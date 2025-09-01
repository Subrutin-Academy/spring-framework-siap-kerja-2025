package com.subrutin.catalog.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.subrutin.catalog.domain.Book;
import com.subrutin.catalog.dto.query.BookQueryDTO;

public interface BookRepository extends JpaRepository<Book, Long>{

    public Page<Book> findAllByTitleLikeIgnoreCase(String title, Pageable pageable);

	
	@Query("""
			SELECT new com.subrutin.catalog.dto.query.BookQueryDTO(
			b.id, b.title, b.description, p.id, p.name) 
			FROM  Book b 
			JOIN Publisher p ON p.id = b.publisher.id 
			WHERE b.id=:id
			""")
	public Optional<BookQueryDTO> findBookByIdUsingJpaProjection(Long id);
	
	
	@Query("""
			SELECT new com.subrutin.catalog.dto.query.BookQueryDTO(
			b.id, b.title, b.description, p.id, p.name) 
			FROM  Book b 
			JOIN Publisher p ON p.id = b.publisher.id 
			WHERE UPPER(b.title) LIKE UPPER(:title)
			""")
	public Page<BookQueryDTO> findBookListUsingJpaProjection(String title, Pageable pageable);

	
}
