package com.subrutin.catalog.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record AuthorDetailResponseDTO(
		Long id,
		String name,
		String birthPlace,
		Long birthDate,
		String description
		) {

}
