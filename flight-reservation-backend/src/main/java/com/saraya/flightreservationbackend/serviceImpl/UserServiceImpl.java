package com.saraya.flightreservationbackend.serviceImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import com.saraya.flightreservationbackend.repository.BookingDetailsRepo;
import com.saraya.flightreservationbackend.repository.FlightDetailsRepo;
import com.saraya.flightreservationbackend.repository.PassengerRepo;
import com.saraya.flightreservationbackend.repository.UserRepo;
import com.saraya.flightreservationbackend.model.BookingDetails;
import com.saraya.flightreservationbackend.model.FlightDetails;
import com.saraya.flightreservationbackend.model.Passenger;
import com.saraya.flightreservationbackend.model.User;
import com.saraya.flightreservationbackend.exception.*;
import com.saraya.flightreservationbackend.service.UserService;
import com.saraya.flightreservationbackend.utils.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;

	@Autowired
	FlightDetailsRepo flightRepo;

	@Autowired
	BookingDetailsRepo bookingRepo;
	
	@Autowired
	PassengerRepo passengerRepo;

	@Override
	public User addUser(User user) {

		if (user == null)
			throw new NullUserException("No data recieved");
		Integer userId = (int) ((Math.random() * 900) + 100);
		user.setUserId(userId);
		Optional<User> checkUser = userRepo.findById(user.getUserId());
		if (checkUser.isPresent())
			throw new UserAlreadyExistException("user already exists");

		userRepo.save(user);
		System.out.println("user Added");
		return user;

	}

	
	@Override
	public void updateUser(User user) {
		if (user == null)
			throw new NullUserException("No data recieved");
		Optional<User> checkUser = userRepo.findById(user.getUserId());
		if (checkUser.isPresent())
			userRepo.save(user);
		else
			throw new UserDoesnotExistException("User not found");

	}

	
	@Override
	public User getUser(Integer userId) {
		if (userId == null)
			throw new NullUserException("No data recieved");
		Optional<User> user = userRepo.findById(userId);
		if (!user.isPresent())
			throw new UserDoesnotExistException("User not found");
		return user.get();
	}

	
	@Override
	public void deleteUser(Integer userId) {
		if (userId == null)
			throw new NullUserException("No data recieved");
		Optional<User> user = userRepo.findById(userId);
		if (!user.isPresent())
			throw new UserDoesnotExistException("User not found");
		userRepo.deleteById(userId);
	}


    /*@Override
    public User userLogin(String userName) {

        Optional<User> user = userRepo.findByUserName(userName);

    }*/

	@Override
	public User userLogin(UserAuth auth) {
		if (auth == null) {
			throw new NullUserException("No data recieved");
		}
		Optional<User> user = userRepo.findById(auth.getUserId());
		if (user.isPresent()) {
			if (user.get().getUserId() == auth.getUserId() && user.get().getPassword().equals(auth.getPassword())) {
				return user.get();
			} else {
				throw new UserDoesnotExistException("invalid login id or password");
			}

		} else {
			throw new UserDoesnotExistException("User not found");
		}
	}
	@Override
	public BookingDetails addBooking(BookingDetails booking, Integer userId, Integer flightNumber) {
		Optional<User> user = userRepo.findById(userId);
		Optional<FlightDetails> flight = flightRepo.findById(flightNumber);
		if (!user.isPresent()) {
			throw new UserDoesnotExistException("user id not found");
		}
		if (!flight.isPresent()) {
			throw new FlightDetailsNotFoundException("flight details not found");
		}
		Integer bookingId = (int) ((Math.random() * 9000) + 1000);
		booking.setBookingId(bookingId);
		booking.setOwnerId(userId);
		booking.setFlightNumber(flightNumber);
		booking.setBookingDate(LocalDate.now().toString());
		booking.setBookingTime(LocalTime.now().toString().substring(0, 5));
		booking.setTotalCost(flight.get().getCost() * booking.getPassengers().size());
		List<BookingDetails> bookingList = user.get().getBookingDetails();
		bookingList.add(booking);
		user.get().setBookingDetails(bookingList);
		updateUser(user.get());
		return bookingRepo.getReferenceById(bookingId);
	}

	@Override
	public void deleteBooking(Integer bookingId, Integer userId) {
		Optional<User> u = userRepo.findById(userId);
		Optional<BookingDetails> bd = bookingRepo.findById(bookingId);
		if (!bd.isPresent()) {
			throw new UserDoesnotExistException("booking not found");
		}
		if (!u.isPresent()) {
			throw new UserDoesnotExistException("user id not found");
		}
		User user = u.get();
		List<BookingDetails> bookingList = user.getBookingDetails();
		BookingDetails deleteBooking = null;
		for (BookingDetails b : bookingList) {
			if (b.getBookingId() == bookingId) {
				System.out.println("booking id found");
				deleteBooking = b;
			}
		}
		bookingList.remove(deleteBooking);
		user.setBookingDetails(bookingList);
		bookingRepo.deleteById(bookingId);
		updateUser(user);
	}

	@Override
	public List<BookingDetails> getBookingByUserId(Integer userId) {
		Optional<User> user = userRepo.findById(userId);
		if (!user.isPresent()) {
			throw new UserDoesnotExistException("user id not found");
		}
		return user.get().getBookingDetails();
	}

	@Override
	public FlightDetails findByRouteAndDate(String arrivalAirport, String departureAirport, String date) {
		List<FlightDetails> list = flightRepo.findByRouteDate(arrivalAirport.toLowerCase(),
				departureAirport.toLowerCase());
		for (FlightDetails f : list) {
			if (f.getDepartureDate().equals(date)) {
				return f;
			}
		}
		throw new FlightDetailsNotFoundException("details not found");
	}
	
	
	@Override
	public FlightDetails getFlightByFlightNumber(Integer flightNumber) {
		if (flightNumber == null) {
			throw new NullFlightDetailsException("no data privided");
		}
		Optional<FlightDetails> details = flightRepo.findById(flightNumber);
		if (!details.isPresent()) {
			throw new FlightDetailsNotFoundException("no flight found for given number");
		}
		return details.get();
	}
	
	@Override
	public Passenger updatePassenger(Passenger passenger) {
		if (passenger == null) {
			throw new PassengerNotFoundException("no data provided");
		}
		Optional<Passenger> oldPassenger = passengerRepo.findById(passenger.getPassengerId());
		if (!oldPassenger.isPresent()) {
			throw new PassengerNotFoundException("passenger not found");
		}
		passengerRepo.save(passenger);
		return passenger;
	}

}
