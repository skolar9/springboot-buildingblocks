package com.springboot.restservices.exceptions;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomErrorDetails customErrorDetails=new CustomErrorDetails(new Date(),"From handleMethodArgumentNotValid global exceptin handler",ex.getMessage());
		return new ResponseEntity<>(customErrorDetails,HttpStatus.BAD_REQUEST);
	}
	
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomErrorDetails customErrorDetails=new CustomErrorDetails(new Date(),"From handleHttpRequestMethodNotSupported from global exceptin handler",ex.getMessage());
		return new ResponseEntity<>(customErrorDetails,HttpStatus.METHOD_NOT_ALLOWED);
	
	}
	@ExceptionHandler(UserNameNotFoundException.class)
	public final ResponseEntity<Object> handleUserNameNotFoundException(UserNameNotFoundException ex,WebRequest request){
		CustomErrorDetails customErrorDetails=new CustomErrorDetails(new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(customErrorDetails,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,WebRequest request){
		CustomErrorDetails customErrorDetails=new CustomErrorDetails(new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(customErrorDetails,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(OrderNotFoundException.class)
	public final ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException ex,WebRequest request){
		CustomErrorDetails customErrorDetails=new CustomErrorDetails(new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(customErrorDetails,HttpStatus.NOT_FOUND);
	}
}
