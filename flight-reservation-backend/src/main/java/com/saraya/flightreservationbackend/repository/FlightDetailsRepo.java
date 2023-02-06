package com.saraya.flightreservationbackend.repository;

import java.util.List;

import com.saraya.flightreservationbackend.model.FlightDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface FlightDetailsRepo extends JpaRepository<FlightDetails, Integer> {

	@Query("select f from FlightDetails f where f.arrivalAirport = ?1 and f.departureAirport = ?2")
	public List<FlightDetails> findByRouteDate(String sourceAirport, String destinationAirport);
	
}
