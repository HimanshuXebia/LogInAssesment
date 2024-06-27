package com.example.test.service;

import com.example.test.request.OtpVerificationRequestDto;
import com.example.test.request.ResetPasswordRequestDto;

public interface ResetPasswordService {
	
	Boolean verifyOtp(OtpVerificationRequestDto otpVerificationRequestDto) throws Exception;

	String resetPassword(ResetPasswordRequestDto resetPasswordRequestDto) throws Exception;
}
