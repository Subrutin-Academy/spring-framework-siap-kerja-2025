package com.subrutin.catalog.service.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.subrutin.catalog.domain.Author;
import com.subrutin.catalog.dto.AuthorCreateRequestDTO;
import com.subrutin.catalog.dto.AuthorDetailResponseDTO;
import com.subrutin.catalog.dto.AuthorListResponseDTO;
import com.subrutin.catalog.dto.AuthorUpdateRequestDTO;
import com.subrutin.catalog.dto.query.AuthorQueryDTO;
import com.subrutin.catalog.exception.ResourceNotFoundException;
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


	@Override
	public Set<Author> findAllAuthors(List<Long> authorsId) {
		Set<Author> authors= authorRepository.findAllByIdIn(authorsId);
		if(authors.isEmpty()||(authors.size()!=authorsId.size())) {
			throw new ResourceNotFoundException("authors not found");
		}
		return authors;
	}


	@Override
	public Map<Long, Set<AuthorListResponseDTO>> findAuthorMap(Set<Long> bookIdSet) {
		Set<AuthorQueryDTO> authorsQuery= authorRepository.findAuthorsByBookIdSet(bookIdSet);
		Map<Long, Set<AuthorListResponseDTO>> authorsMap = new HashMap<>();
		Set<AuthorListResponseDTO> authorsDTO =null;
		for(AuthorQueryDTO q:authorsQuery) {
			if(!authorsMap.containsKey(q.bookId())) {
				authorsDTO = new HashSet<>();
			}else {
				authorsDTO = authorsMap.get(q.bookId());
			}
			authorsDTO.add(new AuthorListResponseDTO(q.authorId(), q.authorName()));
			authorsMap.put(q.bookId(), authorsDTO);
		}
		return authorsMap;
	}
	
	
	

}
