package com.springboot.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.restservices.entity.User;
import com.springboot.restservices.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired	
	private UserRepository ur;
	
	public List<User> getAllUsers() {
		
		return ur.findAll();	
	}
	
	public User createUser(User user){
		
		return ur.save(user);	
	}
	
	public Optional<User> getUserById(Long id)
	{
		Optional<User> user = ur.findById(id);
		return user;
	}

	public User updateUserById(Long id, User user) {
		user.setId(id);
		return  ur.save(user);
	}
	
	public void deleteUserByid(Long id) {
		if(ur.findById(id).isPresent()){
			ur.deleteById(id);
		}
		
	}
	public User getUserByuserNam(String userName) {
		return ur.findByUserName(userName);
	}
}
