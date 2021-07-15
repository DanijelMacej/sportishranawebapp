package com.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dto.EMailDto;
import com.dto.UserDto;

public class CustomUserRowMapper implements RowMapper<UserDto> {

	@Override
	public UserDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		UserDto userDto = new UserDto();
		EMailDto eMailDto = new EMailDto();
		userDto.setFirstName(rs.getString("firstName"));
		userDto.setLastName(rs.getString("lastName"));
		userDto.setUsername(rs.getString("username"));
		userDto.setPassword(rs.getString("password"));
		userDto.setConfirmpassword(rs.getString("confirmpassword"));
		userDto.setCity(rs.getString("city"));
		userDto.setGender(rs.getString("gender"));
		userDto.setCheck(rs.getBoolean("chack"));
		userDto.setEnabled(rs.getBoolean("enabled"));
		userDto.setVerificationcode(rs.getString("verificationcode"));
		eMailDto.setEmail(rs.getString("email"));
		userDto.setMailDto(eMailDto);
		
		
		return userDto;
	}
	
	
	

}
