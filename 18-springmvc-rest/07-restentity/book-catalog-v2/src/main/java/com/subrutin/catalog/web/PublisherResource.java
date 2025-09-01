package com.subrutin.catalog.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.subrutin.catalog.dto.PublisherListRecordDTO;
import com.subrutin.catalog.dto.PublisherListResponseDTO;

@RestController
@RequestMapping("/v1/publishers")
public class PublisherResource {
	
	
	//2xx
	//4xx
	@GetMapping //GET /v1/publishers
	public ResponseEntity<PublisherListRecordDTO> hello() {
//		PublisherListResponseDTO dto = new PublisherListResponseDTO();
//		dto.setId(1L);
//		dto.setName("Manning Publications");
		
		return ResponseEntity.accepted().body(new PublisherListRecordDTO(1L, "Manning Publications"));
	}

}
