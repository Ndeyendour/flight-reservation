package com.saraya.flightreservationbackend.service;

import com.saraya.flightreservationbackend.model.Admin;
import com.saraya.flightreservationbackend.model.FlightDetails;
import com.saraya.flightreservationbackend.model.Passenger;
import com.saraya.flightreservationbackend.utils.AdminAuth;

import java.util.List;



public interface AdminService {
	public Admin addAdmin(Admin admin);

	public Admin getAdmin(Integer adminId);

	public void deleteAdmin(Integer adminId);

	public Admin adminLogin(AdminAuth auth);

	public List<FlightDetails> getAllFlightDetails();

	public FlightDetails addFlightDetails(FlightDetails details);

	public void deleteFlight(Integer flightNumber);

	public FlightDetails updateFlight(FlightDetails details);
	
	public List<Passenger> getAllPassengers();
	
	public List<Passenger> getPassengersByBooking(Integer id);

}
