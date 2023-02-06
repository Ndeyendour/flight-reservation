package com.saraya.flightreservationbackend.exception;

public class PassengerNotFoundException extends RuntimeException {

	public PassengerNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);

	}

	public PassengerNotFoundException(String arg0) {
		super(arg0);

	}
	
}
