package com.subrutin.catalog.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.subrutin.catalog.dto.ErrorResponseDTO;
import com.subrutin.catalog.enums.ErrorCode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler{

	@ExceptionHandler(exception = ResourceNotFoundException.class)
	protected ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
		List<String> details = new ArrayList<>();
		details.add(ex.getMessage());
		log.error("Exception : ", ex);
		ErrorResponseDTO dto = new ErrorResponseDTO(new Date(), 
				HttpStatus.NOT_FOUND, "data not found", ErrorCode.DATA_NOT_FOUND, details);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);		
	}
	
	@ExceptionHandler(exception = Exception.class)
	protected ResponseEntity<ErrorResponseDTO> handleGeneralException(Exception ex, WebRequest request){
		List<String> details = new ArrayList<>();
		details.add(ex.getMessage());
		log.error("Exception : ", ex);
		ErrorResponseDTO dto = new ErrorResponseDTO(new Date(), 
				HttpStatus.INTERNAL_SERVER_ERROR, "General Exception", ErrorCode.OTHER_ERROR, details);
		return ResponseEntity.badRequest().body(dto);		
	}
	
	@Override
	@Nullable
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<String> details = new ArrayList<>();
		log.error("Exception : ", ex);
		for(ObjectError error : ex.getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		ErrorResponseDTO dto = new ErrorResponseDTO(new Date(), 
				HttpStatus.BAD_REQUEST, "invalid data", ErrorCode.INVALID_DATA, details);
		return ResponseEntity.badRequest().body(dto);
	}

}
