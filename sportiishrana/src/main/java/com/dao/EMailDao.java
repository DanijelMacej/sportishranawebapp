package com.dao;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.eclipse.jdt.internal.compiler.batch.ClasspathDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;

@Repository
public class EMailDao implements IMailDao {
	
	
	@Autowired
	JavaMailSender javaMailSender;
	
	public void sendEmail(String firstname,String email,String subject ,String content) throws MessagingException, UnsupportedEncodingException {
		
		//	SimpleMailMessage mailMessage = new SimpleMailMessage();
		
	    	MimeMessage mailMessage = javaMailSender.createMimeMessage();
	    	MimeMessageHelper helper = new MimeMessageHelper(mailMessage,true);
			
			
		    String mailsubject = firstname + " has sent a message";
		    String mailContent = "<p><b>Sender name :</b>" + firstname + "</p>";
		    mailContent+="<p><b>Sender Email:</b> " + email + "</p>";
		    mailContent+="<p><b>Subject :</b>"+ subject + "</p>";
		    mailContent+="<p><b>Content :</b>" + content + "</p>";
		    mailContent+="<hr><img src='/sportiishrana/URLForImages/sport.jpg'/>";
		    
		    helper.setFrom("springseleniumexpress@gmail.com","Sport-ishrana");
			helper.setTo("sportiishrana@gmail.com");
			helper.setSubject(mailsubject);
			helper.setText( mailContent,true);  // true da bi potvrdili da koristimo html tagove
			
			
			
			javaMailSender.send(mailMessage);
		}
		
	
	

}
