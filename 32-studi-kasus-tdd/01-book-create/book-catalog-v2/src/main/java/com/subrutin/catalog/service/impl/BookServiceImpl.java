package com.subrutin.catalog.service.impl;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.subrutin.catalog.domain.Author;
import com.subrutin.catalog.domain.Book;
import com.subrutin.catalog.domain.Category;
import com.subrutin.catalog.domain.Publisher;
import com.subrutin.catalog.dto.BookCreateRequestDTO;
import com.subrutin.catalog.dto.IdResponseDTO;
import com.subrutin.catalog.repository.BookRepository;
import com.subrutin.catalog.service.AuthorService;
import com.subrutin.catalog.service.BookService;
import com.subrutin.catalog.service.CategoryService;
import com.subrutin.catalog.service.PublisherService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
	
	private final AuthorService authorService;
	
	private final CategoryService categoryService;
	
	private final PublisherService publisherService;
	
	private final BookRepository bookRepository;

	@Override
	public IdResponseDTO createBook(BookCreateRequestDTO dto) {

		//1. find authorlist by authorid
		Set<Author> authors= authorService.findAllAuthors(dto.authorsId());
		//2 find categoryList dari categoryCode
		Set<Category> categories =  categoryService.findCategories(dto.categoriesCode());//NOVEL, novel
		//3 find publisher by publisherId
		Publisher publisher = publisherService.findPublisher(dto.publisherId());
		//4 dto -> entity
		Book book = new Book();
		book.setTitle(dto.title());
		book.setAuthors(authors);
		book.setDescription(dto.description());
		book.setCategories(categories);
		book.setPages(dto.pages());
		book.setPublisher(publisher);
		book.setPublishingYear(dto.publishingYear());
		book =  bookRepository.save(book);
		//5 save
		return new IdResponseDTO(book.getId());
	}

}
