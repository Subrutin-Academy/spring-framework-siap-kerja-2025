package com.subrutin.catalog.dto;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record BookCreateRequestDTO(
		@NotBlank
		String title,
		String description,
		Integer pages,
		Integer publishingYear,
		@NotNull
		Long publisherId,
		@NotEmpty
		List<Long> authorsId,
		@NotEmpty
		List<String> categoriesCode
		) {

}
