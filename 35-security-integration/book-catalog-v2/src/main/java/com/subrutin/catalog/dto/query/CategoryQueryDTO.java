package com.subrutin.catalog.dto.query;

public record CategoryQueryDTO(
		Long bookId,
		String categoryCode,
		String categoryName
		) {

}
