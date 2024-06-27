package com.example.test.request;

import jakarta.validation.constraints.NotEmpty;

public class OtpVerificationRequestDto {
	
	@NotEmpty
	private String userName;
	
	@NotEmpty
	private String otp;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

}
