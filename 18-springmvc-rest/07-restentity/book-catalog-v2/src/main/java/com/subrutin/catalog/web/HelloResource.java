package com.subrutin.catalog.web;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subrutin.catalog.service.GreetingService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequiredArgsConstructor
public class HelloResource {	
	private final GreetingService greetingService;

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
