package com.example.test.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = { ApiException.class })
	public ResponseEntity<Object> handleApiException(ApiException apiException) {
		return ResponseEntity.status(apiException.getHttpStatus()).body(apiException.getBody());
	}
}
