package com.assessment.dbs.order.exception;

public class UniqueConstraintViolationException extends RuntimeException{

	public UniqueConstraintViolationException(String message) {
		super(message);
	}
}
