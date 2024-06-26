package com.example.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.entity.Users;
import com.example.test.request.PasswordResetRequestDto;
import com.example.test.request.UserLoginRequestDto;
import com.example.test.service.LoginService;

import jakarta.validation.Valid;

@RequestMapping("/api/v1/")
@RestController
public class LoginController {
	private final LoginService loginService;

	@Autowired
	public LoginController(LoginService loginService) {
		super();
		this.loginService = loginService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Users>> getAllUsers(){
		List<Users> users = loginService.getAllUsers();
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}
	
	
	
	@PostMapping("/login")
	public ResponseEntity<Users> loginUser(@Valid @RequestBody UserLoginRequestDto userLoginRequestDto) throws Exception{
		Users user = loginService.loginUser(userLoginRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestBody PasswordResetRequestDto passwordResetRequestDto) throws Exception{
		return ResponseEntity.ok(loginService.resetPassword(passwordResetRequestDto));
	}
	
}
