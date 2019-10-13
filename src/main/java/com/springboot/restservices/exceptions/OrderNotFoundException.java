package com.springboot.restservices.exceptions;

public class OrderNotFoundException extends Exception{

	
	private static final long serialVersionUID = 1L;

	public OrderNotFoundException(String message) {
		super(message);
	}

	
}
