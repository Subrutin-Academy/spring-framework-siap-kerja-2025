package com.subrutin.catalog.service.impl;

import org.springframework.stereotype.Service;

import com.subrutin.catalog.domain.Publisher;
import com.subrutin.catalog.dto.PublisherCreateRequestDTO;
import com.subrutin.catalog.dto.PublisherDetailResponseDTO;
import com.subrutin.catalog.dto.PublisherUpdateRequestDTO;
import com.subrutin.catalog.repository.PublisherRepository;
import com.subrutin.catalog.service.PublisherService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PublisherServiceImpl implements PublisherService {

	private final PublisherRepository publisherRepository;

	
	@Override
	public void createPublisher(PublisherCreateRequestDTO dto) {
		//dto->entity
		Publisher publisher = new Publisher();
		publisher.setName(dto.name());
		publisher.setDescription(dto.description());
		
		publisherRepository.save(publisher);
				
	}


	@Override
	public PublisherDetailResponseDTO findPublisherDetail(Long id) {
		Publisher publisher = publisherRepository.findById(id)
		.orElseThrow(()-> new RuntimeException("publisher id not found"));
		
		return new PublisherDetailResponseDTO(publisher.getId(), 
				publisher.getName(), 
				publisher.getDescription());
	}


	@Override
	public void updatePublisher(Long id, PublisherUpdateRequestDTO dto) {
		// publisher by id
		Publisher publisher = publisherRepository.findById(id)
		.orElseThrow(()-> new RuntimeException("publisher id not found"));
		
		// update based on dto
		publisher.setName(dto.name());
		publisher.setDescription(dto.description());		
		//save
		publisherRepository.save(publisher);
	}


	@Override
	public void deletePublisher(Long id) {
		publisherRepository.deleteById(id);
		
	}

}
