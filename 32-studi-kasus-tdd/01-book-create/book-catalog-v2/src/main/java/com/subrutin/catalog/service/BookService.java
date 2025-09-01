package com.subrutin.catalog.service;

import com.subrutin.catalog.dto.BookCreateRequestDTO;
import com.subrutin.catalog.dto.IdResponseDTO;

public interface BookService {
	
	public IdResponseDTO createBook(BookCreateRequestDTO dto);

}
