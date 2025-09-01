package com.subrutin.catalog.service;

import com.subrutin.catalog.dto.AuthorCreateRequestDTO;
import com.subrutin.catalog.dto.AuthorDetailResponseDTO;

public interface AuthorService {
	
	public void createAuthor(AuthorCreateRequestDTO dto);
	
	public  AuthorDetailResponseDTO findAuthor(Long id);

}
