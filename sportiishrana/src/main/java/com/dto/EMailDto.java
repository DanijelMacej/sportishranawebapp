package com.dto;

import javax.validation.constraints.Email;


public class EMailDto {
	
	private String fullName;
	@Email (message = "Vas email nije validan" , regexp = ".+@.+\\..+")
	private String email;
	private String subject;
	private String content;
	
	
	
    
	
	public EMailDto() {
		
		System.out.println("Konstruktoe EmailDto");
	
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	


}
