package com.example.test.request;

import jakarta.validation.constraints.NotEmpty;

public class GenerateOtpRequestDto {
	
	@NotEmpty
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
