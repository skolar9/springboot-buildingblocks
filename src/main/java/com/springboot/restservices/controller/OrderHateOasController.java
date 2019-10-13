package com.springboot.restservices.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restservices.entity.Order;
import com.springboot.restservices.entity.User;
import com.springboot.restservices.exceptions.UserNotFoundException;
import com.springboot.restservices.repository.OrderRepository;
import com.springboot.restservices.repository.UserRepository;

@RestController
@RequestMapping(value="/hateoas/users")
public class OrderHateOasController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping("/{userid}/orders")
	public Resources<Order> getAllOrders(@PathVariable("userid") Long id) throws UserNotFoundException {
			Optional<User> user =userRepository.findById(id);
			if(!user.isPresent())
			{
				throw new UserNotFoundException("User not found");
			}
			List<Order> allOrders = user.get().getOrders();
			Resources<Order> finalResources =new Resources<Order>(allOrders);
			return finalResources;
	}

}
