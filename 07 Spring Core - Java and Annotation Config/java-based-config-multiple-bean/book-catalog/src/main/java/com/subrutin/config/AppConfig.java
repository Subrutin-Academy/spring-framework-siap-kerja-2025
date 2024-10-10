package com.subrutin.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.subrutin.domain.Author;
import com.subrutin.domain.Book;

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
		Book book = new Book("The Lost Art of Scripture",author1);
		return book;
	}
	
	@Bean
	public Book book2(@Qualifier("author2")  Author author2) {
		Book book = new Book("The Learn Startup",author2);
		return book;
	}
}
