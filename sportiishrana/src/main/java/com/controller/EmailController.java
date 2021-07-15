package com.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.customvalidator.EmailCustomValidator;
import com.customvalidator.EmailCustomValidator2;
import com.dao.IMailDao;
import com.dto.EMailDto;

@Controller

public class EmailController {
	
	@Autowired	
	IMailDao imailDao;
	
	
	
	@ModelAttribute("emailDto")
	public EMailDto getMail() {
		System.out.println("emailDto()");
		EMailDto eMailDto = new EMailDto();
		System.out.println("--------------------------------");
		return eMailDto;
	}
	
	
	
	@RequestMapping (value ="/email", method = RequestMethod.GET)
	public String email(Model model) {
		
	System.out.println("email()");
		
	
		
		if (!model.containsAttribute("emailDto")) {
			
			model.addAttribute("emailDto", new EMailDto());
			
		}
		
		
		return "email-page";
	}
	
	
	
	@RequestMapping(value ="/processEmail", method = RequestMethod.POST)
	public String emailprocess(@Valid @ModelAttribute("emailDto")  EMailDto emailDto,BindingResult result,RedirectAttributes redirectAttributes) throws MessagingException, UnsupportedEncodingException {
		
		
		if (result.hasErrors()) {
			
			List<ObjectError> allErrors = result.getAllErrors();
			
			for (ObjectError objectError : allErrors) {
				
				System.out.println(objectError);
				
			}
			
			
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.emailDto",result);
			
			redirectAttributes.addFlashAttribute("emailDto", emailDto);
		
			
			return "redirect:/email";
		}else
			
		
		
		imailDao.sendEmail(emailDto.getFullName(),emailDto.getEmail(),emailDto.getSubject(),emailDto.getContent());
		
		
		return "front-page";
	}
	
	
	
	
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		
		EmailCustomValidator2 emailCustomValidator = new EmailCustomValidator2();
		binder.addValidators(emailCustomValidator);
		
				
		
	}
	
	
}
    


	

