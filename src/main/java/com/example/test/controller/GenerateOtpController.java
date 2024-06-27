package com.example.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.request.GenerateOtpRequestDto;
import com.example.test.service.GenerateOtpService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RequestMapping("api/v1/")
@RestController
public class GenerateOtpController {
	private final GenerateOtpService generateOtpService;

	@Autowired
	public GenerateOtpController(GenerateOtpService generateOtpService) {
		super();
		this.generateOtpService = generateOtpService;
	}

	@GetMapping("otp")
	public ResponseEntity<String> getOtp(@Valid @RequestBody GenerateOtpRequestDto generateOtpRequestDto) throws Exception{
		String responseString = generateOtpService.getOtp(generateOtpRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(responseString);
		
	}
	
}
