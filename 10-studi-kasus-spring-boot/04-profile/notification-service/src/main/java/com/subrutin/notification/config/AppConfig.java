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
	public Properties mailProperties(MailSmtpProperties mailProp) {
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", mailProp.getAuth());
		prop.put("mail.smtp.starttls.enable", mailProp.getStartTlsEnable());
		prop.put("mail.smtp.host", mailProp.getHost());
		prop.put("mail.smtp.port", mailProp.getPort());
		prop.put("mail.smtp.ssl.trust", mailProp.getSslTrust());
		return prop;
	}
	
	@Bean
	public PasswordAuthentication passwordAuthentication(MailSmtpProperties mailProp) {
		return new PasswordAuthentication(mailProp.getUsername(), mailProp.getPassword());
		
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
