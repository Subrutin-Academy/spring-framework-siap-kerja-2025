package com.subrutin.catalog.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {
	
	@GetMapping("/hello")
	public String helloWorld() {
		return "Hello World!, this message from Spring Boot";
	}

}
