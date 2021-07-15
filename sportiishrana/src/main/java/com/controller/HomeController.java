package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.UserDto;

@Controller

public class HomeController {
	
	@ModelAttribute("userDto")
    public UserDto getUserDto() {
    	System.out.println("INSIDE getUserDto");
    	UserDto userDto = new UserDto();
    	return userDto;
    }
	
	@RequestMapping("/home")
	public String frontPage(@ModelAttribute("userDto")UserDto userDto) {
		
		
		
		System.out.println("inside home()");
		
		return "front-page";
	}
	
	
	
	@RequestMapping("/headerFrontPageLink")
	public String frontPageHeaderLink() {
		
		return "information-page";
	}
	
	
	

}
