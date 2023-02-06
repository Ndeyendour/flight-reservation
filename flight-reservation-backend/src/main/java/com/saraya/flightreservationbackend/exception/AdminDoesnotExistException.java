package com.saraya.flightreservationbackend.exception;

public class AdminDoesnotExistException extends RuntimeException {

	public AdminDoesnotExistException(String message, Throwable cause) {
		super(message, cause);

	}

	public AdminDoesnotExistException(String message) {
		super(message);

	}

}
