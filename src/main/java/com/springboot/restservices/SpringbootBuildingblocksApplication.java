package com.springboot.restservices;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.springboot.restservices.*"})
public class SpringbootBuildingblocksApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBuildingblocksApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
