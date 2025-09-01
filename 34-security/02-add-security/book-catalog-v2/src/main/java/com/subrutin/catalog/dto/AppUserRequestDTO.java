package com.subrutin.catalog.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record AppUserRequestDTO(
		@NotBlank
		String name,
		@NotBlank @Pattern(regexp ="^\\+\\d{1,3}-\\d{10,13}$" )
		String mobileNumber, //+62-81234567890
		@NotBlank @Email
		String email,
		ProfileRequestDTO profile
		) {

}
