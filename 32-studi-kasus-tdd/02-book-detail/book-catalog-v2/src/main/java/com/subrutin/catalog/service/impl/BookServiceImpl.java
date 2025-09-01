package com.subrutin.catalog.service.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.subrutin.catalog.domain.Author;
import com.subrutin.catalog.domain.Book;
import com.subrutin.catalog.domain.Category;
import com.subrutin.catalog.domain.Publisher;
import com.subrutin.catalog.dto.AuthorListResponseDTO;
import com.subrutin.catalog.dto.BookCreateRequestDTO;
import com.subrutin.catalog.dto.BookDetailResponseDTO;
import com.subrutin.catalog.dto.CategoryListResponseDTO;
import com.subrutin.catalog.dto.IdResponseDTO;
import com.subrutin.catalog.dto.PublisherListResponseDTO;
import com.subrutin.catalog.exception.ResourceNotFoundException;
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

	@Override
	public BookDetailResponseDTO findBookDetail(Long id) {
		//get book
		Book book = bookRepository.findById(id)
			.orElseThrow(()-> new ResourceNotFoundException("book id not found"));
		//get publisher
		Publisher publisher =  book.getPublisher();
		//get authors
		Set<Author> authors =  book.getAuthors();
		
		//get category
		Set<Category> categories = book.getCategories();
		//author-> dto
		Set<AuthorListResponseDTO> authorsDTO = authors.stream().map(a->{
			return new AuthorListResponseDTO(a.getId(), a.getName());
		}).collect(Collectors.toSet());
		//category ->dto
		Set<CategoryListResponseDTO> categoriesDTO = categories.stream().map(c->{
			return new CategoryListResponseDTO(c.getCode(), c.getName());
		}).collect(Collectors.toSet());
		//publisher -> dto
		PublisherListResponseDTO publisherDTO = new PublisherListResponseDTO(publisher.getId(), 
				publisher.getName());
		//book -> dto
		return new BookDetailResponseDTO(book.getId(), 
				book.getTitle(), 
				book.getDescription(), 
				book.getPages(), 
				book.getPublishingYear(), 
				publisherDTO, 
				authorsDTO, 
				categoriesDTO);
	}

}





