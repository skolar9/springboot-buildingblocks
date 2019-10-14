package com.springboot.restservices.controller;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.springboot.restservices.entity.User;
import com.springboot.restservices.entity.Views;
import com.springboot.restservices.exceptions.UserNotFoundException;
import com.springboot.restservices.services.UserService;

@RestController
@RequestMapping("/jsonview/users")
public class UserJsonViewContrloller {

	@Autowired
	UserService userService;
	
	@GetMapping("/external/{id}")
	@JsonView(Views.External.class)
	public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id){
		try{
			return userService.getUserById(id);	
		}catch(UserNotFoundException ex)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
	}
	@GetMapping("/internal/{id}")
	@JsonView(Views.Internal.class)

	public Optional<User> getUserByIdInternal(@PathVariable("id") @Min(1) Long id){
		try{
			return userService.getUserById(id);	
		}catch(UserNotFoundException ex)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
	}
}
