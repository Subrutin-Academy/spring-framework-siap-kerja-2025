package com.subrutin.catalog.dto;
	
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.subrutin.catalog.validator.annotation.PastDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record AuthorCreateRequestDTO(
		@NotBlank
		String name,
		@NotBlank
		String birthPlace,
		@NotNull @PastDate
		Long birthDate, //yyyy/MM/dd
		
		@Size(max = 2000)
		String description
		) {

}
