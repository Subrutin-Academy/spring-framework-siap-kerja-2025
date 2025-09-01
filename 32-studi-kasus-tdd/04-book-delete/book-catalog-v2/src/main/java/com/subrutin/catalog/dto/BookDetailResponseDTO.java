package com.subrutin.catalog.dto;

import java.util.Set;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record BookDetailResponseDTO(
		Long id, 
		String title, 
		String description, 
		Integer pages, 
		Integer publishingYear,
		PublisherListResponseDTO publisher, 
		Set<AuthorListResponseDTO> authors,
		Set<CategoryListResponseDTO> categories
		) {

}
