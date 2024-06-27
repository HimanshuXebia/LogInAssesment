package com.example.test.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.test.dao.RegisterUserRepo;
import com.example.test.entity.Users;
import com.example.test.exception.ApiException;
import com.example.test.request.PasswordChangeRequestDto;
import com.example.test.request.UserLoginRequestDto;
import com.example.test.service.LoginService;
import com.example.test.util.ConstantUtil;

@Service
public class LoginServiceImpl implements LoginService {
	
	private final RegisterUserRepo registerUserRepo;
	
	@Autowired
	public LoginServiceImpl(RegisterUserRepo registerUserRepo) {
		super();
		this.registerUserRepo = registerUserRepo;
	}


	private Users isUserPresent(String userName) throws Exception {
		Optional<Users> optionalUser = registerUserRepo.findByUserName(userName);
		if(!optionalUser.isPresent()) {
			throw new ApiException(HttpStatus.BAD_REQUEST, ConstantUtil.USER_NOT_EXIST);
		}
		return optionalUser.get();
	}
	
	private boolean passwordValidation(Users user, UserLoginRequestDto userLoginRequestDto) {
		if(userLoginRequestDto.getPassword().equals(user.getPassword())) {
			// set no of attempts to 0
			user.setNumberOfAttempts(0);
			return true;
		}
		return false;
	}
	

	@Override
	public Users loginUser(UserLoginRequestDto userLoginRequestDto) throws Exception {
		// TODO Auto-generated method stub
		Users user = isUserPresent(userLoginRequestDto.getUserName());
		if(!user.isBlocked()) {
			throw new ApiException(HttpStatus.LOCKED, "User is blocked. Please try again");
		}
		if(user.getBlockedDate().plusHours(ConstantUtil.BLOCK_DURATION).isBefore(LocalDateTime.now())) {
			// Unblock the user
			user.setBlocked(false);
			user.setBlockedDate(null);
			user.setNumberOfAttempts(0);
			registerUserRepo.save(user);
			
		}
		
		if(!passwordValidation(user, userLoginRequestDto)) {
			// increment on failed attempt
			user.setNumberOfAttempts(user.getNumberOfAttempts()+1);
			registerUserRepo.save(user);
			if(user.getNumberOfAttempts() >= ConstantUtil.BLOCK_DURATION) {
				// all the attempts have been failed 
				throw new ApiException(HttpStatus.LOCKED, "User is blocked now.");
			}else {
				throw new ApiException(HttpStatus.BAD_REQUEST, "Password is wrong try again!");
			}
		}else {
			user.setNumberOfAttempts(0);
			registerUserRepo.save(user);
			return user;
		}
	}


	@Override
	public List<Users> getAllUsers() {
		// TODO Auto-generated method stub
		return registerUserRepo.findAll();
	}


	@Override
	public String changePassword(PasswordChangeRequestDto passwordChangeRequestDto) throws Exception {
		// TODO Auto-generated method stub
		Users user = isUserPresent(passwordChangeRequestDto.getUserName());
		if(passwordChangeRequestDto.getCurrentPassword().equals(user.getPassword())) {
			user.setPassword(passwordChangeRequestDto.getNewPassword());
			registerUserRepo.save(user);
			return "Password has been reset successfully";
		}else {
			return "Current Password is incorrect";
		}
	}

}
