package com.subrutin.catalog.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.subrutin.catalog.config.ApplicationProperties;
import com.subrutin.catalog.service.GreetingService;

@Service
public class GreetingServiceImpl implements GreetingService{
	
	private ApplicationProperties applicationProperties;
	
	
	
	public GreetingServiceImpl(ApplicationProperties applicationProperties) {
		super();
		this.applicationProperties = applicationProperties;
	}



	@Override
	public String sayGreeting() {
		return applicationProperties.getWelcomeText()+"; Your TimeZone :"+applicationProperties.getTimezone() +"; your currency:"+applicationProperties.getCurrency();
	}

}
