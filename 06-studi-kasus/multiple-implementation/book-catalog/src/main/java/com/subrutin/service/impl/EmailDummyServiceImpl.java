package com.subrutin.service.impl;

import com.subrutin.service.EmailService;

public class EmailDummyServiceImpl  implements EmailService{

	@Override
	public void sendMail(String destinationAddress, String mailSubject, String messageContent) throws Exception {
		System.out.println("destination address="+destinationAddress);
		System.out.println("mail subject="+mailSubject);
		System.out.println("message content="+messageContent);

		
	}

}
