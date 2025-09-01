package com.subrutin.catalog.dto.query;

public record BookQueryDTO(Long bookId,
		String bookTitle,
		String bookDescription,
		Long publisherId,
		String publisherName) {

}
