package com.springboot.restservices.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.springboot.restservices.entity.Order;
import com.springboot.restservices.entity.User;
import com.springboot.restservices.exceptions.UserNotFoundException;
import com.springboot.restservices.repository.UserRepository;
import com.springboot.restservices.services.UserService;

@RestController
@RequestMapping(value="/hateoas/users")
@Validated
public class UserHatoasController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public Resources<User> getAllUsers() throws UserNotFoundException{
		
		List<User> users = userService.getAllUsers();
		for(User user:users)
		{
			Long userid =  user.getUserid();
			Link selfLink=ControllerLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
			user.add(selfLink);	
			Resources<Order> orders = ControllerLinkBuilder.methodOn(OrderHateOasController.class).getAllOrders(userid);
			Link ordersLink = ControllerLinkBuilder.linkTo(orders).withRel("all-orders");
			user.add(ordersLink);
		}
		
		Link selfLink=ControllerLinkBuilder.linkTo(this.getClass()).withSelfRel();
		Resources <User> finalResource=new Resources<User>(users,selfLink);
		
		return finalResource;
		
	}
	@GetMapping("/{id}")
	public Resource<User> getUserById(@PathVariable("id") @Min(1) Long id){
		try{
			Optional<User> optionaluser = userService.getUserById(id);	
			User user = optionaluser.get();
			Long userid =  user.getUserid();
			Link selfLink=ControllerLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
			user.add(selfLink);
			Resource <User> finalResource=new Resource<User>( user);
			return finalResource;
		}catch(UserNotFoundException ex)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
	}
}
