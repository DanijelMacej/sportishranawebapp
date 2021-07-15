package com.controller;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.customvalidator.EmailCustomValidator;
import com.customvalidator.PasswordCustomValidator;
import com.dao.IUserDao;
import com.dto.UserDto;

@Controller
@SessionAttributes("userDto")
public class LoginController {
	
	@Autowired
	IUserDao  iuserDao;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@ModelAttribute("userDto")
    public UserDto getUserDto() {
  
    	UserDto userDto = new UserDto();
  
    	return userDto;
    }
	
	
	
	@GetMapping("/login")
	public String login(@ModelAttribute("userDto")UserDto userDto,RedirectAttributes redirectAttributes) {
		
	
		//If he did't logged, send him to the login page
		
		
		boolean chackAuthenticated = iuserDao.chackAuthenticated();

		if (!chackAuthenticated) {

			return "login-page";
		}
       //if he did, try sending him to the home page again
		if (chackAuthenticated) {
			redirectAttributes.addFlashAttribute("error",
					"Postovani " + userDto.getUsername() + " vi ste vec prijavljeni na nalog");
		}
		return "redirect:/homepage";
	}	


	
	
	
	//post -> redirect -> get
	@PostMapping("/process-login")
	public String procesLogin(@ModelAttribute("userDto")UserDto userDto,BindingResult result,Model model, RedirectAttributes redirectAttributes) {
		
		
		
	  
   	 return "redirect:/homepage";

		
	}
	
	
	
	@GetMapping("/homepage")
	public String homePage(@ModelAttribute("userDto")UserDto userDto,Model model,  Principal principal) {
		
		
		
		if (!model.containsAttribute("userDto")) {
			model.addAttribute("userDto", new UserDto());
		}
        
		
		
		return "home-page";
	}
	
	
	@RequestMapping("/accessdenided-page")
	public String accessDenidedPage() {
		
		
		return "accessdennided - page";
	}
	
	
	@RequestMapping( value = "/forgotpassword", method = RequestMethod.GET)
	public String forfotPassword(Model model) {
		
		if (!model.containsAttribute("userDto")) {
			model.addAttribute("userDto", new UserDto());
		}
         
	
		return "forgotpassword";

		}
		
	
	
	
	@RequestMapping( value = "/process-forgotpassword", method = RequestMethod.POST )
	
	public String processResetPassword(@Valid  @ModelAttribute("userDto")UserDto userDto,BindingResult result,RedirectAttributes redirectAttributes) throws UnsupportedEncodingException, MessagingException {
		
		//validate an empty field
		Validator validator = new EmailCustomValidator();
		validator.validate(userDto, result);
	
		
		boolean chackMail = iuserDao.chackMail(userDto.getMailDto().getEmail());

		if (result.hasErrors()) {
			List<ObjectError> allErrors = result.getAllErrors();
			
			for (ObjectError objectError : allErrors) {
				
				System.out.println(objectError);
				
				System.out.println(objectError);
			}
			
			
		redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userDto",result);
		redirectAttributes.addFlashAttribute("userDto", userDto);
			
			return  "redirect:/forgotpassword";
		}

		// if the entered mail does not exist, we basically send the user to the password change page where we print the error
		if (!chackMail) {
			result.rejectValue("mailDto.email", "mailDontExsistInDatabases");
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userDto",result);
			redirectAttributes.addFlashAttribute("userDto", userDto);
			System.out.println(result);
			return "redirect:/forgotpassword";

			
		}else
		
        //if he exist in the  databases we send him link to reset password to the mail
	
			
			iuserDao.sendEmail(userDto);
			return "redirect:/forgotpassword?confrmemail";
		}


	
	// method that is activated when the user clicks the link to reset the password on his email
	@RequestMapping(value ="/mailVerification" , method = RequestMethod.GET)
	
	public String mailVerification(@RequestParam(name = "mail") String mail,Model model,HttpServletRequest request) {
		
		//we catch mail value
		HttpSession session = request.getSession();
		session.setAttribute("mail", mail);
	  
		
	
			return "redirect:/redirectAttributesFromProcesChangePassword";

		}
		
		
	// method used to redirectAtributes from processChangePasswordPage () methods
	// accept and print to changePassword-page.jsp page

	@RequestMapping("/redirectAttributesFromProcesChangePassword")
	public String redirectAttributesFromProcesChangePassword(Model model) {

		if (!model.containsAttribute("userDto")) {
			model.addAttribute("userDto", new UserDto());
		}

		return "changePassword-page";
	}

	// method that checks - validates whether the user has entered the code
	// according to the logic from PasswordCustomValidator (To have at least one
	// capital letter, one number and at least 8 characters)
	// and if so, change the password
	@RequestMapping(value = "/process-changepassword", method = RequestMethod.POST)

	public String processChangePasswordPage(@Valid @ModelAttribute("userDto") UserDto userDto, Model model,
			BindingResult result, RedirectAttributes redirectAttributes, HttpSession session,
			HttpServletRequest request) {

		boolean chackPassword = iuserDao.chackPassword(userDto);

		Validator validator = new PasswordCustomValidator();
		validator.validate(userDto, result);

   
		// We send errors via redirect to the redirectAttributesFromProcesChangePassword
		// () method and therefore use redirectAttributes
		// to print otherwise it will not print
		if (result.hasFieldErrors("password")) {

			result.getFieldError("password");
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userDto", result);
			redirectAttributes.addFlashAttribute("userDto", userDto);

			return "redirect:/redirectAttributesFromProcesChangePassword";

		}

		// we check if he repeated the same digit and in the confirmation password field
	if (!chackPassword) {
		
		result.rejectValue("confirmpassword", "errorConfirmPasswor");
		redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userDto",result);
		redirectAttributes.addFlashAttribute("userDto", userDto);
		return"redirect:/redirectAttributesFromProcesChangePassword";
	}
	
	// if there are no errors updatePassword () method will change the password	

	if (chackPassword) {
		
		
		String encode = passwordEncoder.encode(userDto.getPassword());
	    userDto.setPassword(encode);
	    String encode2 = passwordEncoder.encode(userDto.getConfirmpassword());
	    userDto.setConfirmpassword(encode2);
	 // get the value of the e-mail and place it in the method
	    String mail = (String)session.getAttribute("mail");
		iuserDao.updatepassword(userDto.getPassword(), userDto.getConfirmpassword(), mail);
		session.invalidate();
		HttpSession session2 = request.getSession(false);
		System.out.println(session2);
		System.out.println(mail + "ovo");
		return "redirect:/mylogin";
		
		
	}
	
	return "redirect:/mylogin";
	
	
	
	
	


		
		 
		
	}
     
     
     
   
  

	
}







         

