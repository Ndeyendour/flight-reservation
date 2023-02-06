package com.saraya.flightreservationbackend.repository;

import com.saraya.flightreservationbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    //Optional<User> findByUserName(String userName);

   //@Query("select u from User u where u.userName = ?1")

  // Optional<User> findByUserName(String userName);
}
