package com.controller;

import java.io.UnsupportedEncodingException;
import java.text.Normalizer.Form;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.customvalidator.CheckCustomValidator;
import com.customvalidator.DateCustomValidator;
import com.customvalidator.GenderCustomValidator;
import com.customvalidator.LastNameCustomValidator;
import com.customvalidator.PasswordCustomValidator;
import com.customvalidator.UserNameCustomValidator;
import com.customvalidator.firstNameCustomValidator;
import com.dao.IUserDao;
import com.dto.UserDto;

@Controller
public class RegistrationController{

	@Autowired
	private IUserDao userDao;
	@Autowired
	private PasswordEncoder passwordencoder;
	

	@ModelAttribute("userDto")
    public UserDto getUserDto() {
    	System.out.println("INSIDE getUserDto");
    	UserDto userDto = new UserDto();
  
    	return userDto;
    }

	
  
  @RequestMapping( value ="/registration", method = RequestMethod.GET)	
  public String registrationPage(Model model){
	  
	    
	
	System.out.println("inside registraion()");
		 
		 if (!model.containsAttribute("userDto")) {
			
			 model.addAttribute("userDto", new UserDto());
		}
		
	
	  return "registration-page";
}
  
  
  @RequestMapping(value ="/process", method= RequestMethod.POST)
  public String processPage(@Valid @ModelAttribute("userDto") UserDto userDto , BindingResult result,RedirectAttributes redirectAttributes) throws UnsupportedEncodingException, MessagingException {
	  
	 
	  
		System.out.println("inside process()");
		if (result.hasErrors()) {

			List<ObjectError> allErrors = result.getAllErrors();

			for (ObjectError objectError : allErrors) {

				System.out.println(objectError);

			}

			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userDto", result);

			redirectAttributes.addFlashAttribute("userDto", userDto);

			return "redirect:/registration";

		}
	          
		boolean chackUser = userDao.chackUser(userDto.getUsername());
		if (!chackUser) {

			result.rejectValue("username", "userExsist");
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userDto", result);
			redirectAttributes.addFlashAttribute("userDto", userDto);
			return "redirect:/registration";
		}

		boolean chackPassword = userDao.chackPassword(userDto);
		if (!chackPassword) {

			result.rejectValue("confirmpassword", "errorConfirmPasswor");
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userDto", result);
			redirectAttributes.addFlashAttribute("userDto", userDto);
			return "redirect:/registration";
		}

		boolean chackRegistrationMail = userDao.chackRegistrationMail(userDto.getMailDto().getEmail());

		if (!chackRegistrationMail) {

			result.rejectValue("mailDto.email", "errorEmailExist");
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userDto", result);
			redirectAttributes.addFlashAttribute("userDto", userDto);
			return "redirect:/registration";

		}
	          
	       
		if (chackUser || chackPassword || chackRegistrationMail) {

			String encode = passwordencoder.encode(userDto.getPassword());
			userDto.setPassword(encode);
			String encode2 = passwordencoder.encode(userDto.getConfirmpassword());
			userDto.setConfirmpassword(encode2);
			userDto.setEnabled(false);

			userDao.insertUser(userDto);
			System.out.println("PROCCES METODA");

			userDao.sendVerificationEmail(userDto);

			return "redirect:/verificationeMessage?confirmregistration";

		}

		return "redirect:/registration";
                
	      
	          
  }

	@RequestMapping("/verificationeMessage")
	public String verificationeMessagePage() {

		return "verificationeMessage-page";
	}
  
  
  
	@RequestMapping("/verify")
	public String verifyAccount(@RequestParam("code") String code) {
		System.out.println(code);

		System.out.println("inside verify()");
		// If the verification code is not empty, perform an update for the enabled
		// column based on the verification code
		// Only in this way the user can only log in. See the MySecurityConfig class
		// where I am as a condition
		// gave only views all if enabled = 1
		

		if (!code.isEmpty()) {

			userDao.updateEnabled(code);
            
			return "redirect:/verificationSucessPage";
		}

		return "redirect:/verificationSucessPage";

	}
  
  
  @RequestMapping("/verificationSucessPage")
  public String verificationSucessPage(@ModelAttribute("userDto")UserDto userDto) {
		
		
		
		return "verificationSucessPage-page";
	}
  
  
 
	  
  
  
  @InitBinder
  public void initBinder(WebDataBinder binder) {
	  
	  firstNameCustomValidator firstNameCustomValidator = new firstNameCustomValidator();
	  binder.addValidators(firstNameCustomValidator);
	  
	  LastNameCustomValidator lastNameCustomValidator = new LastNameCustomValidator();
	  binder.addValidators(lastNameCustomValidator);
	  
	  UserNameCustomValidator userNameCustomValidator = new UserNameCustomValidator();
	  binder.addValidators(userNameCustomValidator);
	  
	  PasswordCustomValidator passwordCustomValidator = new PasswordCustomValidator();
	  binder.addValidators(passwordCustomValidator);
	  
	  DateCustomValidator customValidator = new DateCustomValidator();
	  binder.addValidators(customValidator);
	  
	  GenderCustomValidator genderCustomValidator = new GenderCustomValidator();
	  binder.addValidators(genderCustomValidator);
	  
	  CheckCustomValidator chackCustomValidator = new CheckCustomValidator();
	  binder.addValidators(chackCustomValidator);
	  
	  
	  
	/*  
	  SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	  CustomDateEditor customDateEditor = new CustomDateEditor(dateFormat, true);
	  binder.registerCustomEditor(java.sql.Date.class, "date", customDateEditor);*/
	  
	  
	 
	  
	  
	  
	  
	  
  }
}


