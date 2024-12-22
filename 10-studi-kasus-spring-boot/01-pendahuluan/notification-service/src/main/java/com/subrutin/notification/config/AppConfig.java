package com.subrutin.notification.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;

@Configuration
public class AppConfig {


	@Bean
	public Properties mailProperties() {
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", true);
		prop.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
		prop.put("mail.smtp.port", 2525);
		prop.put("mail.smtp.ssl.trust", "sandbox.smtp.mailtrap.io");
		return prop;
	}
	
	@Bean
	public PasswordAuthentication passwordAuthentication() {
		return new PasswordAuthentication("d1268cdd35952e", "34ff9375651117");
		
	}
	
	@Bean
	public Session mailSession(@Qualifier("mailProperties") Properties mailProperties, PasswordAuthentication passwordAuthentication) {
		return Session.getInstance(mailProperties, new Authenticator() {
			@Override
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return passwordAuthentication;
		    }
		});
	}
	


}
