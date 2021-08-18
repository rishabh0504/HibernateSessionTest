package com.example.hibernatetest.exceptions;

public class TransactionFailureException extends RuntimeException {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransactionFailureException(String message) {
	        super(message);
	 }
}
