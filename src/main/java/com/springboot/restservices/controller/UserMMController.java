package com.springboot.restservices.controller;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restservices.dtos.UserMMDTO;
import com.springboot.restservices.entity.User;
import com.springboot.restservices.exceptions.UserNotFoundException;
import com.springboot.restservices.services.UserService;

@RestController
@Validated
@RequestMapping("/modelmapper/users")
public class UserMMController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/{id}")
	public UserMMDTO getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException{
		
			Optional<User> userOptional = userService.getUserById(id);	
		
			if(!userOptional.isPresent()){
				throw new UserNotFoundException("USer Not found");
			}
			User user = userOptional.get();
			UserMMDTO dto =modelMapper.map(user,UserMMDTO.class);
			
			return dto;		
	}

}
