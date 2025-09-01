package com.subrutin.catalog.service;

import com.subrutin.catalog.dto.PublisherCreateRequestDTO;
import com.subrutin.catalog.dto.PublisherDetailResponseDTO;

public interface PublisherService {
	
	public void createPublisher(PublisherCreateRequestDTO dto);
	
	public PublisherDetailResponseDTO findPublisherDetail(Long id);

}
