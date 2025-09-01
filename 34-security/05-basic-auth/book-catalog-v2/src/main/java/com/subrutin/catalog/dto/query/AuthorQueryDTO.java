package com.subrutin.catalog.dto.query;

public record AuthorQueryDTO(
		Long bookId,
		Long authorId,
		String authorName) {

}
