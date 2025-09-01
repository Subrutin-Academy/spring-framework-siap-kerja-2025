package com.subrutin.catalog.service;

import java.util.List;

import com.subrutin.catalog.dto.AuthorCreateRequestDTO;
import com.subrutin.catalog.dto.AuthorDetailResponseDTO;
import com.subrutin.catalog.dto.AuthorListResponseDTO;
import com.subrutin.catalog.dto.AuthorUpdateRequestDTO;

public interface AuthorService {
	
	public void createAuthor(AuthorCreateRequestDTO dto);
	
	public  AuthorDetailResponseDTO findAuthor(Long id);
	
	public List<AuthorListResponseDTO> findAuthorList(String name);
	
	public void updateAuthor(Long id, AuthorUpdateRequestDTO dto);

}
