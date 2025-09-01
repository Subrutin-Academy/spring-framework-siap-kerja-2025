package com.subrutin.catalog.service.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.subrutin.catalog.domain.Author;
import com.subrutin.catalog.dto.AuthorCreateRequestDTO;
import com.subrutin.catalog.repository.AuthorRepository;
import com.subrutin.catalog.service.AuthorService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {
	
	private final AuthorRepository authorRepository;


	@Override
	public void createAuthor(AuthorCreateRequestDTO dto) {
		//dto-> author
		Author author = new Author();
		author.setName(dto.name());
		author.setBirthPlace(dto.birthPlace());
		author.setBirthDate(LocalDate.ofEpochDay(dto.birthDate()));
		author.setDescription(dto.description());
		
		//save authorrepository
		authorRepository.save(author);
		
	}

}
