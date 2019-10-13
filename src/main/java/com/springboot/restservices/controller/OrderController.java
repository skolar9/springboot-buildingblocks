package com.springboot.restservices.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restservices.entity.Order;
import com.springboot.restservices.entity.User;
import com.springboot.restservices.exceptions.OrderNotFoundException;
import com.springboot.restservices.exceptions.UserNotFoundException;
import com.springboot.restservices.repository.OrderRepository;
import com.springboot.restservices.repository.UserRepository;

@RestController
@RequestMapping(value="/users")
public class OrderController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping("/{userid}/orders")
	public List<Order> getAllOrders(@PathVariable("userid") Long id) throws UserNotFoundException {
			Optional<User> user =userRepository.findById(id);
			if(!user.isPresent())
			{
				throw new UserNotFoundException("User not found");
			}
			return user.get().getOrders();
	}
	
	@PostMapping("/{userid}/orders")
	public void createOrder(@PathVariable("userid") Long userid,@RequestBody Order order) throws UserNotFoundException {
		Optional<User> userOptional =userRepository.findById(userid);
		if(!userOptional.isPresent())
		{
			throw new UserNotFoundException("User not found");
		}
		User user=userOptional.get();
		order.setUser(user);
		orderRepository.save(order);
		
	}
	
	@GetMapping("/{userid}/orders/{orderid}")
	public Order getOrderByOrderId(@PathVariable("userid") Long userid,@PathVariable("orderid") Long orderid) throws UserNotFoundException, OrderNotFoundException {
		Optional<User> userOptional =userRepository.findById(userid);
		if(!userOptional.isPresent())
		{
			throw new UserNotFoundException("User not found");
		}
		User user=userOptional.get();
		List<Order> orders=user.getOrders();
		for (Order o : orders) {
			System.out.println(o.getOrderId());
			if (orderid.equals(o.getOrderId())) {
				System.out.println("Equal");
				return o;
			}
		}
		throw new OrderNotFoundException("Order not found for the user"+userid);
	}
}
