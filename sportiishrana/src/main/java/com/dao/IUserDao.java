package com.dao;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import com.dto.UserDto;

public interface IUserDao {
	
	
	int getuserId(String userName);
	
	int getcityId(String country );
	
	int getgenderId (String gender);
	
    void insertUser(UserDto userDto);
    
    boolean chackUser(String username);
    
    boolean chackPassword(UserDto userDto);
    
    boolean chackRegistrationMail(String email);
    
    void sendVerificationEmail(UserDto userDto ) throws MessagingException, UnsupportedEncodingException ;
  
    void  updateEnabled(String verification);
  
    boolean chackenabled(UserDto dto);
  
    void sendEmail(UserDto userDto) throws UnsupportedEncodingException, MessagingException;
  
    boolean chackMail(String mail);
  
    void updatepassword(String pass,String confirm,String mail);
     
    public boolean chackAuthenticated();

    
    
    
    

}
