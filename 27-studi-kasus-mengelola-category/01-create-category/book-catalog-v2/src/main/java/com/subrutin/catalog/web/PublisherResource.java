package com.subrutin.catalog.web;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.subrutin.catalog.dto.PublisherCreateRequestDTO;
import com.subrutin.catalog.dto.PublisherDetailResponseDTO;
import com.subrutin.catalog.dto.PublisherListRecordDTO;
import com.subrutin.catalog.dto.PublisherListResponseDTO;
import com.subrutin.catalog.dto.PublisherUpdateRequestDTO;
import com.subrutin.catalog.dto.ResultPageResponseDTO;
import com.subrutin.catalog.service.PublisherService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/publishers")
public class PublisherResource {
	
	private final PublisherService publisherService;
	
	@GetMapping("{id}")
	public ResponseEntity<PublisherDetailResponseDTO> findPublisherDetail(@PathVariable Long id) {
		PublisherDetailResponseDTO dto = publisherService.findPublisherDetail(id);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping
	public ResponseEntity<Void> createNewPublisher(@RequestBody PublisherCreateRequestDTO dto) {
		publisherService.createPublisher(dto);
		return ResponseEntity.created(URI.create("/v1/publishers")).build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Void> updatePublisher(@PathVariable Long id, @RequestBody PublisherUpdateRequestDTO dto){
		publisherService.updatePublisher(id, dto);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ResponseEntity<ResultPageResponseDTO<PublisherListResponseDTO>> findPublisherList(
			@RequestParam(defaultValue = "0") Integer pages,
			@RequestParam(defaultValue = "10") Integer limit,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc") String direction,
			@RequestParam(required = false) String publisherName	
			){
		return ResponseEntity.ok(publisherService.findAllPublisher(pages, limit, sortBy, direction, publisherName));
	}
	
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deletePublisher(@PathVariable Long id){
		publisherService.deletePublisher(id);
		return ResponseEntity.noContent().build();
	}

}
