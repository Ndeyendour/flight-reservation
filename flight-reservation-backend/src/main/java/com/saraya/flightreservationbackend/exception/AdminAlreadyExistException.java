package com.saraya.flightreservationbackend.exception;

public class AdminAlreadyExistException extends RuntimeException {

	public AdminAlreadyExistException(String message, Throwable cause) {
		super(message, cause);

	}

	public AdminAlreadyExistException(String message) {
		super(message);

	}

	
	
}
