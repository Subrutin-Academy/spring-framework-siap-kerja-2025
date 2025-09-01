package com.subrutin.webcatalog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.subrutin.webcatalog.domain.Author;
import com.subrutin.webcatalog.dto.AuthorCreateRequestDTO;
import com.subrutin.webcatalog.dto.AuthorResponseDTO;
import com.subrutin.webcatalog.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService{
	private List<Author> authorList;
	
	public AuthorServiceImpl(List<Author> authorList) {
		super();
		this.authorList = authorList;
	}

	@Override
	public List<AuthorResponseDTO> findAuthorList() {
		return authorList.stream().map(a -> {
			return new AuthorResponseDTO(a.getName(), a.getDescription());
		}).collect(Collectors.toList());
	}

	@Override
	public void createNewAuthor(AuthorCreateRequestDTO dto) {
		Author author = new Author();
		author.setName(dto.name());
		author.setDescription(dto.description());
		authorList.add(author);		
	}

}
