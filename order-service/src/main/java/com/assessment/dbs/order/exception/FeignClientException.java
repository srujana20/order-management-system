package com.assessment.dbs.order.exception;

public class FeignClientException extends RuntimeException {

	public FeignClientException(String message) {
		super(message);
	}
}
