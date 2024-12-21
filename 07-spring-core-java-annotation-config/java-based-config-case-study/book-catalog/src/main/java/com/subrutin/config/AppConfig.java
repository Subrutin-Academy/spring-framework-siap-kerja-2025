package com.subrutin.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.subrutin.domain.Author;
import com.subrutin.domain.Book;
import com.subrutin.service.AuthService;
import com.subrutin.service.EmailService;
import com.subrutin.service.impl.AuthServiceImpl;
import com.subrutin.service.impl.EmailDummyServiceImpl;
import com.subrutin.service.impl.EmailServiceImpl;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;

@Configuration
public class AppConfig {

	@Bean
	public Author author1() {
		Author author = new Author();
		author.setId(1L);
		author.setName("Karen Armstrong");
		return author;
	}

	@Bean
	public Author author2() {
		Author author = new Author();
		author.setId(2L);
		author.setName("Eric Ries");
		return author;
	}

	@Bean
	public Book book1(@Qualifier("author1") Author author1) {
		Book book = new Book("The Lost Art of Scripture", author1);
		return book;
	}

	@Bean
	public Book book2(@Qualifier("author2") Author author2) {
		Book book = new Book("The Learn Startup", author2);
		return book;
	}

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
	
	@Bean
	public EmailService emailService(Session mailSession) {
		return new EmailServiceImpl(mailSession);
	}
	
	@Bean
	public EmailService emailDummyService() {
		return new EmailDummyServiceImpl();
	}
	
	@Bean
	public AuthService authService(@Qualifier("emailDummyService") EmailService emailService) {
		return new AuthServiceImpl(emailService);
	}

}
