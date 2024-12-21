package com.subrutin;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
			new EmailService("sandbox.smtp.mailtrap.io", 25, "d1268cdd35952e", "34ff9375651117").sendMail();
	        System.out.println("send");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
    }
}
