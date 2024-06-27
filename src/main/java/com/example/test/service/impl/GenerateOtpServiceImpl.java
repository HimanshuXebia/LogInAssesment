package com.example.test.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.test.dao.RegisterUserRepo;
import com.example.test.entity.Users;
import com.example.test.exception.ApiException;
import com.example.test.request.GenerateOtpRequestDto;
import com.example.test.service.GenerateOtpService;
import com.example.test.util.ConstantUtil;

import jakarta.validation.Valid;

@Service
public class GenerateOtpServiceImpl implements GenerateOtpService{
	
	private final RegisterUserRepo registerUserRepo;
	
	@Autowired
	public GenerateOtpServiceImpl(RegisterUserRepo registerUserRepo) {
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
	
	private String generateOtp() {
		// Generate a 6 digit random number
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		return String.valueOf(otp);
		
	}


	@Override
	public String getOtp(@Valid GenerateOtpRequestDto generateOtpRequestDto) throws Exception {
		// TODO Auto-generated method stub
		Users user = isUserPresent(generateOtpRequestDto.getUsername());
		String generatedOtp = generateOtp();
		user.setOtp(generatedOtp);
		user.setOtpGeneratedTime(LocalDateTime.now());
		registerUserRepo.save(user);
		return generatedOtp+" is your Otp.";
	}

}
