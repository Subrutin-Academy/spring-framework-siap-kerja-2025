package com.subrutin.catalog.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subrutin.catalog.service.GreetingService;

@RestController
public class HelloResource {
	
	Logger log = LoggerFactory.getLogger(HelloResource.class);
	
	private final GreetingService greetingService;
	
	public HelloResource(GreetingService greetingService) {
		super();
		this.greetingService = greetingService;
	}

	@GetMapping("/hello")
	public String helloWorld() {
		log.error("This is error message");
		log.warn("This is warn message");
		log.info("This is info message");
		log.debug("This is debug message");
		log.trace("This is trace message");
	
		return greetingService.sayGreeting();
	}

}
