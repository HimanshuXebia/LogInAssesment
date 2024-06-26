package com.example.test.service;

import com.example.test.request.UserRegistrationRequestDto;

public interface RegistrationService {
	String registerUser(UserRegistrationRequestDto userRegistrationRequestDto);
}
