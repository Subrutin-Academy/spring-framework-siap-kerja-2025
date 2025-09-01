package com.subrutin.catalog.service;

import org.springframework.data.domain.Sort.Direction;

import com.subrutin.catalog.dto.PublisherCreateRequestDTO;
import com.subrutin.catalog.dto.PublisherDetailResponseDTO;
import com.subrutin.catalog.dto.PublisherListResponseDTO;
import com.subrutin.catalog.dto.PublisherUpdateRequestDTO;
import com.subrutin.catalog.dto.ResultPageResponseDTO;

public interface PublisherService {
	
	public void createPublisher(PublisherCreateRequestDTO dto);
	
	public PublisherDetailResponseDTO findPublisherDetail(Long id);
	
	public void updatePublisher(Long id, PublisherUpdateRequestDTO dto);
	
	public void deletePublisher(Long id);
	
	public ResultPageResponseDTO<PublisherListResponseDTO> findAllPublisher(
			Integer pages,
			Integer limit,
			String sortBy,
			String direction,
			String publisherName
			);

}
