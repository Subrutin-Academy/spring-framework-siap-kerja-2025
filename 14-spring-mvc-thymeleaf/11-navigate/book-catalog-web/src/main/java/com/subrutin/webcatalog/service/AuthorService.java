package com.subrutin.webcatalog.service;

import java.util.List;

import com.subrutin.webcatalog.dto.AuthorCreateRequestDTO;
import com.subrutin.webcatalog.dto.AuthorResponseDTO;

public interface AuthorService {

	public List<AuthorResponseDTO> findAuthorList();
	
	public void createNewAuthor(AuthorCreateRequestDTO dto);
}
