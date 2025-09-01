package com.subrutin.catalog.dto;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ResultPageResponseDTO<T>(
		List<T> result,
		Integer pages,
		Long elements
		) {

}
