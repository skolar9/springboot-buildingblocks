package com.springboot.restservices.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.springboot.restservices.entity.User;
import com.springboot.restservices.exceptions.UserExistsException;
import com.springboot.restservices.exceptions.UserNameNotFoundException;
import com.springboot.restservices.exceptions.UserNotFoundException;
import com.springboot.restservices.services.UserService;

@RestController
@Validated
@RequestMapping(value="/users")
public class UserController {

	@Autowired
	UserService userService;
	
	
	@GetMapping
	public List<User> getAllUsers(){
		
		return userService.getAllUsers();
		
	}
	
	@PostMapping
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user,UriComponentsBuilder builder)
	{
		try {
			 userService.createUser(user);
			 HttpHeaders headers=new HttpHeaders();
			 headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getUserid()).toUri());
			 return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
		} catch (UserExistsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	@GetMapping("/{id}")
	public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id){
		try{
			return userService.getUserById(id);	
		}catch(UserNotFoundException ex)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public  User updateUserById(@PathVariable("id") Long id,@RequestBody User user) {
		return userService.updateUserById(id,user);

	}
	
	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable("id") Long id)
	{
		 userService.deleteUserByid(id);
	}
	
	@GetMapping("/username/{username}")
	public User getUserByuserNam(@PathVariable("username") String userName) throws UserNameNotFoundException {

		User user = userService.getUserByuserNam(userName);
		if(user == null){
			
				throw new UserNameNotFoundException("UserName "+userName+" Not found in repository");
			
		}
		return user;
	}
	
}
