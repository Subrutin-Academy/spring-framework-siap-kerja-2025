package com.subrutin.service.impl;

import com.subrutin.service.EmailService;

import jakarta.mail.Message;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class EmailServiceImpl implements EmailService {
	
	private final Session session;
	
	

	
	 public EmailServiceImpl(Session session) {
		super();
		this.session = session;
	}




	public void sendMail(String destinationAddress, String mailSubject, String messageContent) throws Exception {

	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress("from@gmail.com"));
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinationAddress));
	        message.setSubject(mailSubject);
	        String msg = messageContent;
	        
	        MimeBodyPart mimeBodyPart = new MimeBodyPart();
	        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");
	        Multipart multipart = new MimeMultipart();
	        multipart.addBodyPart(mimeBodyPart);

	        message.setContent(multipart);

	        Transport.send(message);
	    }
}
