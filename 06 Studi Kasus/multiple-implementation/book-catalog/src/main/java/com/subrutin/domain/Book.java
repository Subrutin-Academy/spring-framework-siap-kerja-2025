package com.subrutin.domain;

public class Book {
	
	private String title;
	
	private Author author;
	
	public Book(Author author) {
		this.author = author;
	}
	
	

	public Book(String title, Author author) {
		super();
		this.title = title;
		this.author = author;
	}



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	
}
