package com.subrutin.catalog.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.subrutin.catalog.service.GreetingService;

@Service
public class GreetingServiceImpl implements GreetingService{
	
	@Value("${welcome.text}")
	private String welcomeText;
	
	@Value("${time-zone}")
	private String timeZone;
	
	@Value("${currency}")
	private String currency;

	@Override
	public String sayGreeting() {
		return this.welcomeText+"; Your TimeZone :"+timeZone +"; your currency:"+currency;
	}

}
