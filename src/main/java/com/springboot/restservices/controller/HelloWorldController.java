package com.springboot.restservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restservices.entity.UserDetails;

@RestController
public class HelloWorldController {
	
	//@RequestMapping(method=RequestMethod.GET,path="/helloWorld")
	@GetMapping("/helloWorld")
	public String helloWorld(){
		return "Hello World";
	}
	
	@GetMapping("/helloWorldBean")
	public UserDetails helloWorldBean(){
		
		return new UserDetails("sreekanth","kolar","Bangalore");
	}

}
