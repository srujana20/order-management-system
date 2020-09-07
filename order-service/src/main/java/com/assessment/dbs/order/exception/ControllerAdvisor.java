package com.assessment.dbs.order.exception;


import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> hanldeNotFoundError(NotFoundException ex, WebRequest request) {
		ApiError error = new ApiError(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), LocalDateTime.now(),
				ex.getMessage(), ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApiError error = new ApiError(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(),
				ex.getMessage(), ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UniqueConstraintViolationException.class)
	public ResponseEntity<Object> handleUniqueConstraintError(UniqueConstraintViolationException ex,
			WebRequest request) {
		ApiError error = new ApiError(HttpStatus.CONFLICT, HttpStatus.CONFLICT.value(), LocalDateTime.now(),
				ex.getMessage(), ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String errors = ex.getBindingResult().getFieldErrors().stream()
				.map(x -> x.getField() + " " + x.getDefaultMessage()).collect(Collectors.joining(","));
		ApiError error = new ApiError(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(),
				errors, errors);
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);	
	}
	
	@ExceptionHandler(DbException.class)
	public ResponseEntity<Object> handleDbException(DbException ex,
			WebRequest request) {
		ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now(),
				ex.getMessage(), ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(FeignClientException.class)
	public ResponseEntity<Object> handleFeignClientException(FeignClientException ex,
			WebRequest request) {
		ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now(),
				ex.getMessage(), ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}

