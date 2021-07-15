package com.customvalidator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dto.UserDto;

public class LastNameCustomValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return UserDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	
		String lastName = ((UserDto)target).getLastName();
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "emptyLasttNameError");
		
		int min = 2;
		int max = 40;
		
		if (lastName.length()<min || lastName.length()>max) {
			
			errors.rejectValue("lastName", "lastNamelengthError");
			
			
		}
		
		
		//Samo string prolazi
		 if (!lastName.matches("[a-zA-Z]*")) {
				
			  errors.rejectValue("lastName", "lastNameOnlyString");
		
		 }

		
	}

}
