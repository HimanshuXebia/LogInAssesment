package com.example.test.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.test.dao.RegisterUserRepo;
import com.example.test.entity.Users;
import com.example.test.exception.ApiException;
import com.example.test.request.OtpVerificationRequestDto;
import com.example.test.request.ResetPasswordRequestDto;
import com.example.test.service.ResetPasswordService;
import com.example.test.util.ConstantUtil;

@Service
public class ResetPasswordServiceImpl implements ResetPasswordService{

	private final RegisterUserRepo registerUserRepo;
	
	@Autowired
	public ResetPasswordServiceImpl(RegisterUserRepo registerUserRepo) {
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
	@Override
	public Boolean verifyOtp(OtpVerificationRequestDto otpVerificationRequestDto) throws Exception {
		// TODO Auto-generated method stub
		Users user = isUserPresent(otpVerificationRequestDto.getUserName());
		boolean otpMatch = user.getOtp().equals(otpVerificationRequestDto.getOtp());
		boolean isOtpTimeInRange = user.getOtpGeneratedTime().plusMinutes(ConstantUtil.OTP_DURATION).isAfter(LocalDateTime.now());
		
		if(!otpMatch) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Otp is mismatched");
		}
		if(!isOtpTimeInRange) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Otp is expired! Please generate new Otp");
		}
		user.setOtpVerified(true);
		registerUserRepo.save(user);
		return true;
	}


	@Override
	public String resetPassword(ResetPasswordRequestDto resetPasswordRequestDto) throws Exception {
		// TODO Auto-generated method stub
		Users user = isUserPresent(resetPasswordRequestDto.getUsername());
		if(!user.isOtpVerified()) {
			throw new ApiException(HttpStatus.BAD_GATEWAY, "Please generate and verify your OTP first!");
		}
		user.setPassword(resetPasswordRequestDto.getPassword());
		user.setOtpVerified(false);
		user.setOtpGeneratedTime(null);
		user.setOtp(null);
		registerUserRepo.save(user);
		return "Password is updated now you can login";
	}

}
