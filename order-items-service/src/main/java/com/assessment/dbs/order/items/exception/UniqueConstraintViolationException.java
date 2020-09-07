package com.assessment.dbs.order.items.exception;

public class UniqueConstraintViolationException extends RuntimeException{

	public UniqueConstraintViolationException(String message) {
		super(message);
	}
}
