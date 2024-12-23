package com.subrutin.notification.service.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.subrutin.notification.service.EmailService;

@Profile("local")
@Service
public class EmailDummyServiceImpl  implements EmailService{

	@Override
	public void sendMail(String destinationAddress, String mailSubject, String messageContent) throws Exception {
		System.out.println("destination address="+destinationAddress);
		System.out.println("mail subject="+mailSubject);
		System.out.println("message content="+messageContent);

		
	}

}
