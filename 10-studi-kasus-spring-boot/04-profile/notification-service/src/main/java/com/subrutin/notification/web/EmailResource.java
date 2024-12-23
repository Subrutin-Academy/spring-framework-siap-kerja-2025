package com.subrutin.notification.web;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subrutin.notification.service.EmailService;

@RestController
public class EmailResource {
	
	private final EmailService emailService;
	

	public EmailResource(EmailService emailService) {
		super();
		this.emailService = emailService;
	}


	@GetMapping("/v1/mail")
	public void sendMail() throws Exception {
		emailService.sendMail("detik19@gmail.com", "Test Notification", "This Sample test");
	}
}
