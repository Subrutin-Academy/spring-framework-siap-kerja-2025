package com.subrutin.catalog.dto;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.subrutin.catalog.enums.ErrorCode;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ErrorResponseDTO(
		Date timestamp,
		HttpStatus status,
		String message, 
		ErrorCode errorCode,
		List<String> details
		) {

}
