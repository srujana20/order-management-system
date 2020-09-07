package com.assessment.dbs.order.items.exception;

public class FeignClientException extends RuntimeException {

	public FeignClientException(String message) {
		super(message);
	}
}
