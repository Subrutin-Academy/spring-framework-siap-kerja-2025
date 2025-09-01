package com.subrutin.catalog.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.subrutin.catalog.domain.Book;
import com.subrutin.catalog.domain.Category;
import com.subrutin.catalog.domain.Publisher;
import com.subrutin.catalog.exception.ResourceNotFoundException;
import com.subrutin.catalog.web.AbstractTest;

import jakarta.transaction.Transactional;

public class BookRepositoryTest extends AbstractTest{
	
    @Autowired
    BookRepository bookRepository;
    //test fetch eager book - publisher
    @Test @Transactional
    void testFetchDirectBookById() {
        Book book = bookRepository.findById(1L)
            .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        assertEquals("Laskar Pelangi", book.getTitle());
    }
    
    @Test @Transactional
    void testFetchBookPublisher() {
        Book book = bookRepository.findById(1L)
            .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        Publisher publisher = book.getPublisher();
        assertEquals("Bentang Pustaka", publisher.getName());
    }

    @Test @Transactional
    void testFetchBookCategories() {
        Book book = bookRepository.findById(24L)
            .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        Set<Category> categories = book.getCategories();
        assertEquals(3, categories.size());
    }

}
