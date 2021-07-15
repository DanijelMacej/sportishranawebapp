package com.customvalidator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dto.UserDto;

@Component
public class EmailCustomValidator implements Validator {
	
	
	

	@Override
	public boolean supports(Class<?> clazz) {
	
		return UserDto.class.equals(clazz);
	}

	
	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mailDto.email", "emailNotEmpty");
	
		
	     
	
	   
         
	   
	}
	
}

