package com.example.test.service;

import java.util.List;

import com.example.test.entity.Users;
import com.example.test.request.PasswordChangeRequestDto;
import com.example.test.request.UserLoginRequestDto;

public interface LoginService {
	
	Users loginUser(UserLoginRequestDto userLoginRequestDto) throws Exception;
	
	List<Users> getAllUsers();
	
	String changePassword(PasswordChangeRequestDto passwordChangeRequestDto) throws Exception;

}
