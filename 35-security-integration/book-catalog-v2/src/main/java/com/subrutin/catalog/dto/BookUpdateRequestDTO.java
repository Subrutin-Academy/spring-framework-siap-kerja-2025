package com.subrutin.catalog.dto;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record BookUpdateRequestDTO(
	    @NotBlank
	    String title,
	    String description,
	    @NotNull
	    Integer pages,
	    @NotNull
	    Integer publishingYear,
	    @NotNull
	    Long publisherId,
	    @NotEmpty
	    List<Long> authorsId,
	    @NotEmpty
	    List<String> categoriesCode
		) {

}
