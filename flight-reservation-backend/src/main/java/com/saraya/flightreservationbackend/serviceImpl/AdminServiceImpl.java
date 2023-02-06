package com.saraya.flightreservationbackend.serviceImpl;

import java.util.List;
import java.util.Optional;


import com.saraya.flightreservationbackend.repository.AdminRepo;
import com.saraya.flightreservationbackend.repository.BookingDetailsRepo;
import com.saraya.flightreservationbackend.repository.FlightDetailsRepo;
import com.saraya.flightreservationbackend.repository.PassengerRepo;
import com.saraya.flightreservationbackend.model.Admin;
import com.saraya.flightreservationbackend.model.BookingDetails;
import com.saraya.flightreservationbackend.model.FlightDetails;
import com.saraya.flightreservationbackend.model.Passenger;
import com.saraya.flightreservationbackend.exception.*;
import com.saraya.flightreservationbackend.service.AdminService;
import com.saraya.flightreservationbackend.utils.AdminAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepo adminRepo;

	@Autowired
	FlightDetailsRepo flightRepo;
	
	@Autowired
	PassengerRepo passengerRepo;
	
	@Autowired
	BookingDetailsRepo bookingRepo;

	
	@Override
	public Admin addAdmin(Admin admin) {
		if (admin == null)
			throw new NullAdminException("no data provided");
		Integer adminId = (int) ((Math.random() * 900) + 100);
		
		admin.setAdminId(adminId);
		Optional<Admin> checkAdmin = adminRepo.findById(admin.getAdminId());
		if (checkAdmin.isPresent()) {
			throw new AdminAlreadyExistException("admin already exist exception");
		} else {
			adminRepo.save(admin);
			System.out.println(adminId);
			return admin;
		}
	}

	@Override
	public Admin getAdmin(Integer adminId) {
		if (adminId == null)
			throw new NullAdminException("no data provided");
		Optional<Admin> admin = adminRepo.findById(adminId);
		if (!admin.isPresent()) {
			throw new AdminDoesnotExistException("admin does not exist ");
		}
		return admin.get();
	}
	
	
	@Override
	public void deleteAdmin(Integer adminId) {
		if (adminId == null)
			throw new NullAdminException("no data provided");
		Optional<Admin> admin = adminRepo.findById(adminId);
		if (!admin.isPresent()) {
			throw new AdminDoesnotExistException("admin Doesnot Exist Exception");
		}
		adminRepo.deleteById(adminId);
	}

	@Override
	public Admin adminLogin(AdminAuth auth) {
		if (auth == null) {
			throw new NullAdminException("no data provided");
		}
		Optional<Admin> admin = adminRepo.findById(auth.getAdminId());
		if (admin.isPresent()) {
			if (admin.get().getAdminId() == auth.getAdminId() && admin.get().getPassword().equals(auth.getPassword())) {
				return admin.get();
			} else {
				throw new AdminDoesnotExistException("invalid login id or password");
			}

		} else
			throw new AdminDoesnotExistException("admin doesnot exist");
	}
	@Override
	public List<FlightDetails> getAllFlightDetails() {
		return flightRepo.findAll();
	}

	@Override
	public FlightDetails addFlightDetails(FlightDetails details) {
		if (details == null) {
			throw new NullFlightDetailsException("no data provided");
		}
		Integer flightNumber = (int) ((Math.random() * 9000) + 1000);
		details.setFlightNumber(flightNumber);
		flightRepo.save(details);
		return details;
	}

	@Override
	public void deleteFlight(Integer flightNumber) {
		if (flightNumber == null)
			throw new NullFlightDetailsException("No data recieved..");
		Optional<FlightDetails> details = flightRepo.findById(flightNumber);
		if (!details.isPresent()) {
			throw new FlightDetailsNotFoundException("Flight Details not found");
		}
		flightRepo.deleteById(flightNumber);
	}

	@Override
	public FlightDetails updateFlight(FlightDetails details) {
		if (details == null)
			throw new NullFlightDetailsException("No data recieved..");
		Optional<FlightDetails> flightDetails = flightRepo.findById(details.getFlightNumber());
		if (!flightDetails.isPresent()) {
			throw new FlightDetailsNotFoundException("Flight with flightNumber: " + details.getFlightNumber() + " not exists..");
		}
		flightRepo.save(details);
		return details;
	}
	
	public List<Passenger> getAllPassengers(){
		return passengerRepo.findAll();
	}
	
	public List<Passenger> getPassengersByBooking(Integer id){
		if (id == null) throw new BookingDoesNotFoundException("no data provided");
		Optional<BookingDetails> details = bookingRepo.findById(id);
		if (!details.isPresent())
			throw new BookingDoesNotFoundException("booking not found");
		return details.get().getPassengers();
	}

}
