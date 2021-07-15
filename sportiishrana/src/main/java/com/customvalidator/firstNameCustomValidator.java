package com.customvalidator;

import org.springframework.validation.Errors;
import org.springframework.validation.SmartValidator;
import org.springframework.validation.ValidationUtils;

import com.dto.UserDto;

public class firstNameCustomValidator implements org.springframework.validation.Validator {



@Override
public boolean supports(Class<?> clazz) {
	
	return  UserDto.class.equals(clazz);
}

@Override
public void validate(Object target, Errors errors) {
	
	
	String firstName = ((UserDto)target).getFirstName();
	
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "emptyFirstNameError");
	
	int min = 2;
	int max = 40;
	
	if (firstName.length()<min || firstName.length()>max) {
		
		errors.rejectValue("firstName", "firstNamelengthError");
		
		
	}
	
	//Samo string prolazi
	String s1 = firstName.trim();
	
	 if (!s1.matches("[a-zA-Z]*")) {
			
		  errors.rejectValue("firstName", "firstNameOnlyString");
	
	 }

	
	
	
	

	
	
}
}
