package com.subrutin.catalog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.subrutin.catalog.dto.PublisherListRecordDTO;
import com.subrutin.catalog.dto.PublisherListResponseDTO;

@Controller
@RequestMapping("/v1/publishers")
public class PublisherResource {
	
	@GetMapping //GET /v1/publishers
	@ResponseBody
	public PublisherListRecordDTO hello() {
//		PublisherListResponseDTO dto = new PublisherListResponseDTO();
//		dto.setId(1L);
//		dto.setName("Manning Publications");
		
		return new PublisherListRecordDTO(1L, "Manning Publications");
	}

}
