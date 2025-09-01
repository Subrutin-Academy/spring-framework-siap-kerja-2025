package com.subrutin.catalog.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.subrutin.catalog.annotation.LogThisArg;

import jakarta.validation.constraints.NotBlank;

@LogThisArg
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record CategoryCreateRequestDTO(
		@NotBlank String code, 
		@NotBlank String name,
		String description
		) {

}
