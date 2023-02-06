package com.saraya.flightreservationbackend.repository;

import com.saraya.flightreservationbackend.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PassengerRepo extends JpaRepository<Passenger, Integer> {

}
