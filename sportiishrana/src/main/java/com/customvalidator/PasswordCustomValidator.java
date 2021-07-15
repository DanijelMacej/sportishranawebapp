package com.customvalidator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dto.UserDto;

public class PasswordCustomValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		String password = ((UserDto)target).getPassword();
		
		if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
			
			errors.rejectValue("password", "passwordregex");
			
		}
		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "emptypasswordError");

		
	}

}
