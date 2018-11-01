package com.messageserv.exception;

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 56619186809953375L;

	
	public ResourceNotFoundException(String errorMessage) {
		super(errorMessage);
	}
	
}
