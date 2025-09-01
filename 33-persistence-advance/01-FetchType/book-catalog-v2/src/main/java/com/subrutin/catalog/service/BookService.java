package com.subrutin.catalog.service;

import com.subrutin.catalog.dto.BookCreateRequestDTO;
import com.subrutin.catalog.dto.BookDetailResponseDTO;
import com.subrutin.catalog.dto.BookUpdateRequestDTO;
import com.subrutin.catalog.dto.IdResponseDTO;

public interface BookService {
	
	public IdResponseDTO createBook(BookCreateRequestDTO dto);
	
	public BookDetailResponseDTO findBookDetail(Long id);
	
	public void updateBook(Long id, BookUpdateRequestDTO dto); 
	
	public void deleteBook(Long id);

}
