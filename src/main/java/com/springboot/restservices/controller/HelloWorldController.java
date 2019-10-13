package com.springboot.restservices.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restservices.entity.UserDetails;

@RestController
public class HelloWorldController {
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	//@RequestMapping(method=RequestMethod.GET,path="/helloWorld")
	@GetMapping("/helloWorld")
	public String helloWorld(){
		return "Hello World";
	}
	
	@GetMapping("/helloWorldBean")
	public UserDetails helloWorldBean(){
		
		return new UserDetails("sreekanth","kolar","Bangalore");
	}
	
	@GetMapping("/hello-init")
	public String getMessagesInI18NFormat(@RequestHeader(name="Accept-Language",required=false) String locale) {
		return messageSource.getMessage("label.hello", null, new Locale(locale));
	}
	
	@GetMapping("/hello-init1")
	public String getMessagesInI18NFormat() {
		return messageSource.getMessage("label.hello", null, LocaleContextHolder.getLocale());
	}

}
