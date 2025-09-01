package com.subrutin.catalog.dto;

import java.util.Set;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record BookListResponseDTO(
	    Long id, 
	    String title, 
	    PublisherListResponseDTO publisher,
	    Set<AuthorListResponseDTO> authors,
	    Set<CategoryListResponseDTO> categories
		) {

}
