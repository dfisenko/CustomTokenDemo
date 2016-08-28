package com.df;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.security.RolesAllowed;

@SpringBootApplication
public class CustomTokenDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomTokenDemoApplication.class, args);
	}
}
