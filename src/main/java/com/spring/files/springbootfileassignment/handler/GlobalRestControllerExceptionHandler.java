package com.spring.files.springbootfileassignment.handler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.spring.files.springbootfileassignment.exception.CustomFileException;
import com.spring.files.springbootfileassignment.model.ErrorData;
import com.spring.files.springbootfileassignment.utils.DateFormatter;

@RestControllerAdvice
public class GlobalRestControllerExceptionHandler {

	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
			.ofPattern(DateFormatter.DATETIME_FORMAT);

	@ExceptionHandler({CustomFileException.class})
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorData handleRuntimeError(CustomFileException ex) {
		return new ErrorData(HttpStatus.BAD_REQUEST.toString(), ex.getMessage(),
				LocalDateTime.now().format(DATE_TIME_FORMATTER));
	}
}
