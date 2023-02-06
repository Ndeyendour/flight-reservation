package com.saraya.flightreservationbackend.service;

import com.saraya.flightreservationbackend.model.BookingDetails;
import com.saraya.flightreservationbackend.model.FlightDetails;
import com.saraya.flightreservationbackend.model.Passenger;
import com.saraya.flightreservationbackend.model.User;
import com.saraya.flightreservationbackend.utils.UserAuth;

import java.util.List;



public interface UserService {
	public User addUser(User user);

	public void updateUser(User user);

	public User getUser(Integer userId);

	public void deleteUser(Integer userId);

	public User userLogin(UserAuth auth);

	public BookingDetails addBooking(BookingDetails booking, Integer userId, Integer flightNumber);

	public void deleteBooking(Integer bookingId, Integer userId);

	public List<BookingDetails> getBookingByUserId(Integer userId);

	public FlightDetails findByRouteAndDate(String arrivalAirport, String departureAirport, String date);
	
	public FlightDetails getFlightByFlightNumber(Integer flightNumber);
	
	public Passenger updatePassenger(Passenger passenger);

}
