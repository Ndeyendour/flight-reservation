package com.saraya.flightreservationbackend.repository;
import com.saraya.flightreservationbackend.model.BookingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface BookingDetailsRepo extends JpaRepository<BookingDetails, Integer> {

}
