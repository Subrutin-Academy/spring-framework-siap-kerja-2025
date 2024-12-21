package com.subrutin;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.subrutin.domain.Author;
import com.subrutin.domain.Book;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("application-context.xml");
        Author author = (Author) appContext.getBean("author2");
        System.out.println(author.getId());
        System.out.println(author.getName());
        
        Book book = (Book) appContext.getBean("book2");
        System.out.println(book.getTitle());
        System.out.println(book.getAuthor().getName());

    }
}
