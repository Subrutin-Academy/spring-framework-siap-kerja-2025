package com.subrutin.catalog.service;

import com.subrutin.catalog.dto.PublisherCreateRequestDTO;
import com.subrutin.catalog.dto.PublisherDetailResponseDTO;
import com.subrutin.catalog.dto.PublisherUpdateRequestDTO;

public interface PublisherService {
	
	public void createPublisher(PublisherCreateRequestDTO dto);
	
	public PublisherDetailResponseDTO findPublisherDetail(Long id);
	
	public void updatePublisher(Long id, PublisherUpdateRequestDTO dto);
	
	public void deletePublisher(Long id);

}
