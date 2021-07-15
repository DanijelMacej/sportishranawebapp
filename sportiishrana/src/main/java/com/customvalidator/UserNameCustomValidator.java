package com.customvalidator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dto.UserDto;

public class UserNameCustomValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	
String username = ((UserDto)target).getUsername();
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "emptyuserNameError");
		
		int min = 2;
		int max = 40;
		
		if (username.length()<min || username.length()>max) {
			
			errors.rejectValue("username", "userNamelengthError");
			
			
		}
		
	}

}
