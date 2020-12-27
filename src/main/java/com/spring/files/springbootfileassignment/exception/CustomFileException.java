package com.spring.files.springbootfileassignment.exception;

import org.springframework.http.HttpStatus;

public class CustomFileException extends RuntimeException{
	
	private final HttpStatus httpStatus;

	public CustomFileException(HttpStatus httpStatus, String msg) {
		super(msg);
		this.httpStatus = httpStatus;
	}
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
