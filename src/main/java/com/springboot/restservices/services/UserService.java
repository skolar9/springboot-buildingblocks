package com.springboot.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.springboot.restservices.entity.User;
import com.springboot.restservices.exceptions.UserExistsException;
import com.springboot.restservices.exceptions.UserNotFoundException;
import com.springboot.restservices.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired	
	private UserRepository ur;
	
	public List<User> getAllUsers() {
		
		return ur.findAll();	
	}
	
	public User createUser(User user) throws UserExistsException{
		User existingUser=ur.findByUserName(user.getUserName());
		if(existingUser!=null)
		{
			throw new UserExistsException("User already exists in repository");
		}
		return ur.save(user);	
	}
	
	public Optional<User> getUserById(Long id) throws UserNotFoundException
	{
		Optional<User> user = ur.findById(id);
		
		if(!user.isPresent()){
			throw new UserNotFoundException("User Not Found in user repository");
		}
		return user;
	}

	public User updateUserById(Long id, User user) {
		user.setUserid(id);
		return  ur.save(user);
	}
	
	public void deleteUserByid(Long id) {
		if(!ur.findById(id).isPresent()){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User Not Found in user repository");
		}
		ur.deleteById(id);
		
	}
	public User getUserByuserNam(String userName) {
		return ur.findByUserName(userName);
	}
	
}
