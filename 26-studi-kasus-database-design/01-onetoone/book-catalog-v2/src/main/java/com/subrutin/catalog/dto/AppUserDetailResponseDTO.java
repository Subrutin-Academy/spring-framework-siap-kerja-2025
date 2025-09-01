package com.subrutin.catalog.dto;

import java.util.UUID;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record AppUserDetailResponseDTO(
		UUID id,
		String name,
		String email,
		String mobileNumber
		) {

}
