package com.subrutin;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.subrutin.domain.Author;
import com.subrutin.domain.Book;
import com.subrutin.service.AuthService;
import com.subrutin.service.impl.EmailServiceImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        System.out.println( "Hello World!" );
        ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("application-context.xml");
//        Author author = (Author) appContext.getBean("author2");
//        System.out.println(author.getId());
//        System.out.println(author.getName());
//        
//        Book book = (Book) appContext.getBean("book2");
//        System.out.println(book.getTitle());
//        System.out.println(book.getAuthor().getName());
        
//        EmailServiceImpl emailService = (EmailServiceImpl) appContext.getBean("authServiceImpl");
//        emailService.sendMail("destination@gmail.com", "Your OTP", "Your OTP is 12345");
         AuthService authService = (AuthService) appContext.getBean("authService");
         authService.login("destination@gmail.com");

    }
}
