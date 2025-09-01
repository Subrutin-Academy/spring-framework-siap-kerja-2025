package com.subrutin.catalog.domain;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "book")
@SQLRestriction("deleted=false")
@Data
public class Book extends AbstractBaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "description", columnDefinition = "VARCHAR(5000)")
	private String description;
	
	@Column(name = "pages")
	private Integer pages;
	
	@Column(name = "publishing_year")
	private Integer publishingYear;
	
	@ManyToOne
	@JoinColumn(name = "publisher_id", nullable = false)
	private Publisher publisher;
	
	@ManyToMany
	@JoinTable(name = "book_author",
			joinColumns = @JoinColumn(name="book_id"),
			inverseJoinColumns = @JoinColumn(name="author_id"))
	private Set<Author> authors = new HashSet<>();
	
	@ManyToMany
	@JoinTable(name = "book_category",
	joinColumns = @JoinColumn(name="book_id"),
	inverseJoinColumns = @JoinColumn(name="category_code"))
	private Set<Category> categories = new HashSet<>(); 
	
	public void addCategory(Category category) {
		this.categories.add(category);
		category.getBooks().add(this);
	}
	
	public void removeCategory(Category category) {
		this.categories.remove(category);
		category.getBooks().remove(this);
	}
	
	public void addAuthor(Author author) {
		this.authors.add(author);
		author.getBooks().add(this);
	}
	
	
	public void removeAuthor(Author author) {
		this.authors.remove(author);
		author.getBooks().remove(this);
	}
}
