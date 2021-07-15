package com.dao;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

public interface IMailDao {
	
	
	void sendEmail(String username, String email, String subject, String content)
			throws MessagingException, UnsupportedEncodingException;

}
