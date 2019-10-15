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

import com.springboot.restservices.dtos.UserDTOV1;
import com.springboot.restservices.dtos.UserDTOV2;
import com.springboot.restservices.entity.User;
import com.springboot.restservices.exceptions.UserNotFoundException;
import com.springboot.restservices.services.UserService;

@RestController
@Validated
@RequestMapping("/versioning/mediatype/users")
public class UserMediaTypeVersioiningController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping(value="{id}",produces="application/v1+json")
	public UserDTOV1 getUserById1(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException{
		
			Optional<User> userOptional = userService.getUserById(id);	
		
			if(!userOptional.isPresent()){
				throw new UserNotFoundException("USer Not found");
			}
			User user = userOptional.get();
			UserDTOV1 dto =modelMapper.map(user,UserDTOV1.class);
			
			return dto;		
	}
	
	@GetMapping(value="{id}",produces="application/vnd.app-v2+json")
	public UserDTOV2 getUserById2(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException{
		
			Optional<User> userOptional = userService.getUserById(id);	
		
			if(!userOptional.isPresent()){
				throw new UserNotFoundException("USer Not found");
			}
			User user = userOptional.get();
			UserDTOV2 dto =modelMapper.map(user,UserDTOV2.class);
			
			return dto;		
	}

}
