package com.subrutin.catalog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.subrutin.catalog.domain.Publisher;
import com.subrutin.catalog.dto.PublisherCreateRequestDTO;
import com.subrutin.catalog.dto.PublisherDetailResponseDTO;
import com.subrutin.catalog.dto.PublisherListResponseDTO;
import com.subrutin.catalog.dto.PublisherUpdateRequestDTO;
import com.subrutin.catalog.dto.ResultPageResponseDTO;
import com.subrutin.catalog.exception.ResourceNotFoundException;
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
		publisherRepository.softDeletePublisher(id);
		
	}

	@Override
	public ResultPageResponseDTO<PublisherListResponseDTO> findAllPublisher(Integer pages, Integer limit, String sortBy,
			String direction, String publisherName) {
		publisherName = StringUtils.isBlank(publisherName)?"%":publisherName+"%";
		Sort sort = Sort.by(new Sort.Order(Direction.valueOf(direction.toUpperCase()), sortBy));
		Pageable pageable = PageRequest.of(pages, limit, sort);
		Page<Publisher> publisherPage =  publisherRepository.findByNameLikeIgnoreCase(publisherName, pageable);
		List<PublisherListResponseDTO> dtos= publisherPage.stream().map((p)->{
			return new PublisherListResponseDTO(p.getId(), p.getName());
		}).collect(Collectors.toList());
		return new ResultPageResponseDTO<>(dtos, publisherPage.getTotalPages(), publisherPage.getTotalElements());
	}


	@Override
	public Publisher findPublisher(Long id) {
		Publisher publisher = publisherRepository.findById(id)
			.orElseThrow(()->new ResourceNotFoundException("publisher not found"));
		return publisher;
	}

	
	
	
	
	
}
