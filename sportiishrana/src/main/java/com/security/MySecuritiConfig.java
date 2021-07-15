package com.security;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.bind.support.SessionStatus;

import com.dao.IUserDao;
import com.dto.UserDto;
/*and enabled=1*/
@EnableWebSecurity
public class MySecuritiConfig  extends  WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource dataSource;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	IUserDao userDao;
@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder)
		.usersByUsernameQuery("SELECT username, password , 1 AS enabled FROM user WHERE username = ? and enabled=1 ")
		.authoritiesByUsernameQuery("SELECT user.username , authorities.roles FROM user, authorities WHERE user.username = ? AND user.id =authorities.id_user  ");
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.authorizeRequests()
		
		.antMatchers("/home").permitAll()
		.antMatchers("/registration").permitAll()
		.antMatchers("/process").permitAll()
		.and()
		.formLogin().loginPage("/login")
		.loginProcessingUrl("/process-login")
		.successForwardUrl("/process-login")
		.failureUrl("/login?error=true")
		.and()
		 .logout()
		// .logoutSuccessUrl("/home")   //ovo ili logoutSuccessHandler
		 .logoutSuccessHandler(new LogoutSuccessHandler() {
		
			@Override
			public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
					throws IOException, ServletException {
			/*	
				String name = authentication.getName();
				System.out.println(name);
		        userDao.updatelogoutstatus(name);*/
	
			response.sendRedirect("/sportiishrana/home");
				
			
			
				
			}
		})
		 
		 
		 .and()
		 .exceptionHandling().accessDeniedPage("/accessdenided-page");
		
	
	}
	
	

}
