package com.subrutin;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.subrutin.config.AppConfig;
import com.subrutin.domain.Author;
import com.subrutin.domain.Book;
import com.subrutin.service.EmailService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        System.out.println( "Hello World!" );
        ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
        Author author = (Author) appContext.getBean("author1");
        System.out.println(author.getId());
        System.out.println(author.getName());
//        
        Book book = (Book) appContext.getBean("book1");
        System.out.println(book.getTitle());
        System.out.println(book.getAuthor().getName());
        
        EmailService emailService = (EmailService) appContext.getBean("emailService");
        emailService.sendMail("destination@gmail.com", "Your OTP", "Your OTP is 12345");

    }
}
