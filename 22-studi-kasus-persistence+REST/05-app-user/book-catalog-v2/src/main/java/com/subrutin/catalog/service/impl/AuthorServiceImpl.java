package com.subrutin.catalog.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.subrutin.catalog.domain.Author;
import com.subrutin.catalog.dto.AuthorCreateRequestDTO;
import com.subrutin.catalog.dto.AuthorDetailResponseDTO;
import com.subrutin.catalog.dto.AuthorListResponseDTO;
import com.subrutin.catalog.dto.AuthorUpdateRequestDTO;
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


	@Override
	public AuthorDetailResponseDTO findAuthor(Long id) {
		// mengambil data dari database
		Author author = authorRepository.findById(id)
		.orElseThrow(()-> new RuntimeException("author id not found"));
		//dimappingkan dto
		return new AuthorDetailResponseDTO(author.getId(), 
				author.getName(), author.getBirthPlace(), 
				author.getBirthDate().toEpochDay(), author.getDescription());
	}


	@Override
	public List<AuthorListResponseDTO> findAuthorList(String name) {
		name = !StringUtils.hasText(name)?"%":name+"%";//ka% -> karen
		List<Author> authorList = authorRepository.findAllByNameLikeIgnoreCase(name);
		List<AuthorListResponseDTO> dtoList= authorList.stream().map(a->{
			return new AuthorListResponseDTO(a.getId(), a.getName());
		}).collect(Collectors.toList());
		return dtoList;
	}


	@Override
	public void updateAuthor(Long id, AuthorUpdateRequestDTO dto) {
		// 1. retrieve from db
		Author author = authorRepository.findById(id)
		.orElseThrow(()-> new RuntimeException("author id not found"));
		
		// 2 update from dto
		author.setName(dto.name());
		author.setBirthPlace(dto.birthPlace());
		author.setBirthDate(LocalDate.ofEpochDay(dto.birthDate()));
		author.setDescription(dto.description());
		authorRepository.save(author);
		
	}


	@Override
	public void deleteAuthor(Long id) {
		authorRepository.deleteById(id);
		
	}
	
	
	

}
