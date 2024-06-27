package com.example.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.entity.Users;
import com.example.test.request.OtpVerificationRequestDto;
import com.example.test.request.ResetPasswordRequestDto;
import com.example.test.service.ResetPasswordService;

@RequestMapping("api/v1/")
@RestController
public class ResetPasswordController {
	private final ResetPasswordService resetPasswordService;

	@Autowired
	public ResetPasswordController(ResetPasswordService resetPasswordService) {
		super();
		this.resetPasswordService = resetPasswordService;
	}
	
	@PostMapping("verify-otp")
	public ResponseEntity<Boolean> verifyOtp(OtpVerificationRequestDto otpVerificationRequestDto) throws Exception{
		Boolean response = resetPasswordService.verifyOtp(otpVerificationRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PostMapping("reset-password")
	public ResponseEntity<String> resetPassword(ResetPasswordRequestDto resetPasswordRequestDto) throws Exception{
		String response = resetPasswordService.resetPassword(resetPasswordRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
