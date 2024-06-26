package com.example.test.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.dao.RegisterUserRepo;
import com.example.test.entity.Users;
import com.example.test.request.UserRegistrationRequestDto;
import com.example.test.service.RegistrationService;

@Service
public class RegistrationServiceImpl  implements RegistrationService{
	
	private final RegisterUserRepo registerUserRepo;
	private final ModelMapper modelMapper;
	
	@Autowired
	public RegistrationServiceImpl(RegisterUserRepo registerUserRepo, ModelMapper modelMapper) {
		super();
		this.registerUserRepo = registerUserRepo;
		this.modelMapper = modelMapper;
	}



	@Override
	public String registerUser(UserRegistrationRequestDto userRegistrationRequestDto) {
		// TODO Auto-generated method stub
		System.out.println("This is my data before updating" + userRegistrationRequestDto.getUserName());
		Users user = modelMapper.map(userRegistrationRequestDto, Users.class);
		
		System.out.println("This is my user after updating"+user.toString());
		registerUserRepo.save(user);
		return "User has created";
	}

}
