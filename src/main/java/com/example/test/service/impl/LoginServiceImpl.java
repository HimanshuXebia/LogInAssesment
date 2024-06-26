package com.example.test.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.dao.RegisterUserRepo;
import com.example.test.entity.Users;
import com.example.test.request.PasswordResetRequestDto;
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
			throw new Exception("Username does not exists in database");
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
		if(user.isBlocked()) {
			if(user.getBlockedDate().plusHours(ConstantUtil.BLOCK_DURATION).isBefore(LocalDateTime.now())) {
				// Unblock the user
				user.setBlocked(false);
				user.setBlockedDate(null);
				user.setNumberOfAttempts(0);
				registerUserRepo.save(user);
			}else {
				throw new Exception("User is blocked. Please try again");
			}
		}
		
		if(!passwordValidation(user, userLoginRequestDto)) {
			// increment on failed attempt
			user.setNumberOfAttempts(user.getNumberOfAttempts()+1);
			registerUserRepo.save(user);
			if(user.getNumberOfAttempts() >= ConstantUtil.BLOCK_DURATION) {
				// all the attempts have been failed 
				throw new Exception("User is blocked now. ");
			}else {
				throw new Exception("Password is wrong try again!");
			}
		}else {
			user.setNumberOfAttempts(0);
			registerUserRepo.save(user);
		}
		return user;
	}


	@Override
	public List<Users> getAllUsers() {
		// TODO Auto-generated method stub
		return registerUserRepo.findAll();
	}


	@Override
	public String resetPassword(PasswordResetRequestDto passwordResetRequestDto) throws Exception {
		// TODO Auto-generated method stub
		Users user = isUserPresent(passwordResetRequestDto.getUserName());
		if(passwordResetRequestDto.getCurrentPassword().equals(user.getPassword())) {
			user.setPassword(passwordResetRequestDto.getNewPassword());
			registerUserRepo.save(user);
			return "Password has been reset successfully";
		}else {
			return "Current Password is incorrect";
		}
	}

}
