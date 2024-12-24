package com.subrutin.catalog.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.subrutin.catalog.config.ApplicationProperties;
import com.subrutin.catalog.domain.Author;
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
		Author author = new Author();
		author.setId(1L);
		author.setName("Karen Armstrong");
		author.setDescription("test description");
		return applicationProperties.getWelcomeText()+"; Your TimeZone :"+applicationProperties.getTimezone() +"; your currency:"+applicationProperties.getCurrency();
	}

}
