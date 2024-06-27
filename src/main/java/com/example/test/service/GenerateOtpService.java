package com.example.test.service;

import com.example.test.request.GenerateOtpRequestDto;

import jakarta.validation.Valid;

public interface GenerateOtpService {

	String getOtp(@Valid GenerateOtpRequestDto generateOtpRequestDto) throws Exception;

}
