package com.subrutin.catalog.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.subrutin.catalog.domain.Author;
import com.subrutin.catalog.domain.Book;
import com.subrutin.catalog.domain.Category;
import com.subrutin.catalog.domain.Publisher;
import com.subrutin.catalog.dto.AuthorListResponseDTO;
import com.subrutin.catalog.dto.BookCreateRequestDTO;
import com.subrutin.catalog.dto.BookDetailResponseDTO;
import com.subrutin.catalog.dto.BookListResponseDTO;
import com.subrutin.catalog.dto.BookUpdateRequestDTO;
import com.subrutin.catalog.dto.CategoryListResponseDTO;
import com.subrutin.catalog.dto.IdResponseDTO;
import com.subrutin.catalog.dto.PublisherListResponseDTO;
import com.subrutin.catalog.dto.ResultPageResponseDTO;
import com.subrutin.catalog.dto.query.BookQueryDTO;
import com.subrutin.catalog.exception.ResourceNotFoundException;
import com.subrutin.catalog.repository.BookRepository;
import com.subrutin.catalog.service.AuthorService;
import com.subrutin.catalog.service.BookService;
import com.subrutin.catalog.service.CategoryService;
import com.subrutin.catalog.service.PublisherService;

import jakarta.transaction.Transactional;
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

	@Transactional
	@Override
	public void updateBook(Long id, BookUpdateRequestDTO dto) {
		//1. find authorlist by authorid
		Set<Author> authors= authorService.findAllAuthors(dto.authorsId());
		//2 find categoryList dari categoryCode
		Set<Category> categories =  categoryService.findCategories(dto.categoriesCode());//NOVEL, novel
		//3 find publisher by publisherId
		Publisher publisher = publisherService.findPublisher(dto.publisherId());	
		
		Book book = bookRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("book id not found"));		
		book.setTitle(dto.title());
		book.setAuthors(authors);
		book.setDescription(dto.description());
		book.setCategories(categories);
		book.setPages(dto.pages());
		book.setPublisher(publisher);
		book.setPublishingYear(dto.publishingYear());
		
		bookRepository.save(book);
	}

	@Transactional
	@Override
	public void deleteBook(Long id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("book id not found"));
		bookRepository.delete(book);
		
	}

	@Override
	public ResultPageResponseDTO<BookListResponseDTO> findBookList(Integer pages, Integer limit, String sortBy,
			String direction, String bookTitle) {
		bookTitle = StringUtils.isBlank(bookTitle)? "%" :bookTitle+"%";
		Sort sort = Sort.by(new Sort.Order(Direction.valueOf(direction.toUpperCase()), sortBy));
		Pageable pageable =PageRequest.of(pages, limit, sort);
		Page<Book> resultPage =  bookRepository.findAllByTitleLikeIgnoreCase(bookTitle, pageable);
		List<BookListResponseDTO> resultDTO= resultPage.stream().map((b)->{
			return new BookListResponseDTO(b.getId(), 
					b.getTitle(),
			new PublisherListResponseDTO(b.getPublisher().getId(),b.getTitle()),
			b.getAuthors().stream().map(a->
				new AuthorListResponseDTO(a.getId(), a.getName())
				).collect(Collectors.toSet()),
			b.getCategories().stream().map(c->
				new CategoryListResponseDTO(c.getCode(), c.getName())
				).collect(Collectors.toSet())
			);
		}).collect(Collectors.toList());
		return new ResultPageResponseDTO<>(resultDTO, resultPage.getTotalPages(), resultPage.getTotalElements());
	}

	@Override
	public ResultPageResponseDTO<BookListResponseDTO> findBookListUsingJpaProjection(Integer pages, Integer limit,
			String sortBy, String direction, String bookTitle) {
		bookTitle = StringUtils.isBlank(bookTitle)? "%" :bookTitle+"%";
		Sort sort = Sort.by(new Sort.Order(Direction.valueOf(direction.toUpperCase()), sortBy));
		Pageable pageable =PageRequest.of(pages, limit, sort);
		
		Page<BookQueryDTO> pageResult= bookRepository.findBookListUsingJpaProjection(bookTitle, pageable);
		Set<Long> bookIdSet= pageResult.stream().map(b->b.bookId()).collect(Collectors.toSet());
		//collect categorySet <- bookIdSet
		Map<Long, Set<CategoryListResponseDTO>> categoriesMap= categoryService.findCategoriesMap(bookIdSet);
		
		//collect authorSet <- bookIdSet
		Map<Long, Set<AuthorListResponseDTO>> authorsMap = authorService.findAuthorMap(bookIdSet);
		
		List<BookListResponseDTO> dtos = pageResult.stream().map(b->{
			PublisherListResponseDTO publisherDTO = 
					new PublisherListResponseDTO(b.publisherId(), b.publisherName());
			return new BookListResponseDTO(
					b.bookId(), 
					b.bookTitle(), 
					publisherDTO, 
					authorsMap.get(b.bookId()), 
					categoriesMap.get(b.bookId()));
		}).collect(Collectors.toList());
		
		return new ResultPageResponseDTO<>(dtos, pageResult.getTotalPages(), pageResult.getTotalElements());
	}

}





