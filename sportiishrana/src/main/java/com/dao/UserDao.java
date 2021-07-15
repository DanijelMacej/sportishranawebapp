package com.dao;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.dto.UserDto;
import com.rowmapper.CustomUserRowMapper;

import net.bytebuddy.utility.RandomString;

@Repository
public class UserDao implements IUserDao {
	
	@Autowired
	private JdbcTemplate  jdbcTemplate;
	
	@Autowired
	JavaMailSender javaMailSender;
	

	@Override
	public int getuserId(String userName) {
		String query = "SELECT id FROM user WHERE username = ?";
		Integer queryForObject = jdbcTemplate.queryForObject(query, new Object[] {userName}, int.class);
		return queryForObject;
	}
  //Ovde sledi nastavak
	@Override
	public int getcityId(String country) {
		String query = "SELECT id_city FROM city WHERE city_name = ?";
		Integer queryForObject1 = jdbcTemplate.queryForObject(query, new Object[] {country}, int.class);
		return queryForObject1;
	}

	@Override
	public int getgenderId(String gender) {
		String query = "SELECT id_gender FROM gender WHERE gender_mf = ?";
		Integer queryForObject2 = jdbcTemplate.queryForObject(query, new Object [] {gender},int.class);
		return queryForObject2;
	}
	
	
	
	
	
	@Override
	public void insertUser(UserDto userDto) {
		
		//random for verification code
		String random = RandomString.make(64);
        userDto.setVerificationcode(random);
		
		
		String queryUser = "INSERT INTO user VALUES (NULL,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object [] argUser = {userDto.getFirstName(),userDto.getLastName(),userDto.getUsername(),userDto.getPassword(),
		getcityId(userDto.getCity()) ,getgenderId(userDto.getGender()),userDto.getDate(),userDto.isCheck(),userDto.isEnabled(),userDto.getVerificationcode(),userDto.getMailDto().getEmail(),userDto.getConfirmpassword()};
		jdbcTemplate.update(queryUser,argUser);
		String queryAuthority = "INSERT INTO authorities values (null,?,?)";
		Object [] argAuthorities = {getuserId(userDto.getUsername()),"USER"};
		jdbcTemplate.update(queryAuthority,argAuthorities);
		
	}
	
	
	public boolean chackUser(String username) {
		
		      String query = "SELECT * FROM user WHERE username = ?";
		      
		      List<UserDto> userList = jdbcTemplate.query(query, new CustomUserRowMapper(), username);
		if (userList.isEmpty()) {
			
			return true;
		}else {
			
			return false;
		}
	
		
	}
	
	
	public boolean chackPassword(UserDto userDto) {
		
		
		if (userDto.getPassword().matches(userDto.getConfirmpassword())) {
			
			return true;
		}else {
			return false;
		}
	}
	
	
	
public boolean chackenabled(UserDto dto) {
	
	if (dto.isEnabled() != true) {
		
		return false;
		
	}else {
		return true;
	}
	
}

	
	public void  updateEnabled(String verification) {
		
		String query = "UPDATE user SET enabled = 1 WHERE  verificationcode = ?";
		Object[]args = {verification};
		jdbcTemplate.update(query,args);
	}
		
	public void sendVerificationEmail(UserDto userDto ) throws MessagingException, UnsupportedEncodingException {
		
		String subject = "Molim vas verifikujte vasu registraciju";
		String senderMail = "SportiIshrana";
		String mailContent = "<p>Dragi korisnice " + userDto.getFirstName() +" "+userDto.getLastName()+ "</p>";
		mailContent += "<p>Molim vas kliknite na link ispod za verifikaciju registracije :</p>";
		
	//	String verifiCode =  + userDto.getVerificationcode();
		mailContent+="<a href=http://localhost:8080/sportiishrana/verify?code=" + userDto.getVerificationcode()+ ">Verifu </a>" ; 
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
		
		helper.setFrom("springseleniumexpress@gmail.com","Sport-ishrana");
		helper.setTo(userDto.getMailDto().getEmail());
		helper.setText(mailContent,true);
		helper.setSubject(subject);
		javaMailSender.send(mimeMessage);
	}
	
	
	
	public void sendEmail(UserDto userDto) throws UnsupportedEncodingException, MessagingException {
		
		
		String subject = "Resetovanje sifre";
		String mailContent = "<p> Dragi korisnice </p>";
		mailContent+= "<p> Molim vas kliknite na link ispod kako bi mogli da resetujemo sifru : <p/>";
		mailContent+="<a href=http://localhost:8080/sportiishrana/mailVerification?mail="+userDto.getMailDto().getEmail() + ">Reset</a>";
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
		
		helper.setFrom("springseleniumexpress@gmail.com","Sport-ishrana");
		helper.setTo(userDto.getMailDto().getEmail());
		helper.setText(mailContent,true);
		helper.setSubject(subject);
		javaMailSender.send(mimeMessage);
		
		
	}
	
	
	public boolean  chackMail(String mail){
		
		String queru = "SELECT * FROM user WHERE email = ?";
		
		List<UserDto> userList = jdbcTemplate.query(queru, new  CustomUserRowMapper(),mail);
		
		if (userList.isEmpty()) {
			return false;
		}else {
			return true;
		}
		
	}
	
	
	
	public boolean chackRegistrationMail(String email) {
		
		
		String query = "SELECT * FROM user WHERE email = ?";
		List<UserDto> userList = jdbcTemplate.query(query, new CustomUserRowMapper(),email );
		
		if (userList.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public void updatepassword(String pass,String confirm, String mail) {
		
		String query = "UPDATE user SET password = ? , confirmpassword = ? where email = ?";
		
		Object[]args = {pass,confirm,mail};
		
		jdbcTemplate.update(query, args);
	}
	
	
	
	
	

public boolean chackAuthenticated() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    

	if (authentication == null || authentication instanceof AnonymousAuthenticationToken ) {
		 
		return false;
   
       }else {
	return true;
}
}
}




