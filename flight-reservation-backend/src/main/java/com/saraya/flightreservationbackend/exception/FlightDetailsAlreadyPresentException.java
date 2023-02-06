/**
 * 
 */
package com.saraya.flightreservationbackend.exception;


public class FlightDetailsAlreadyPresentException extends RuntimeException {


	public FlightDetailsAlreadyPresentException(String message, Throwable cause) {
		super(message, cause);
	}


	public FlightDetailsAlreadyPresentException(String message) {
		super(message);
	}

}
