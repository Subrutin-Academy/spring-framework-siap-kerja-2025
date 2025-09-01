package com.subrutin.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subrutin.catalog.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
