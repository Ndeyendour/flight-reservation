package com.saraya.flightreservationbackend.repository;

import com.saraya.flightreservationbackend.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRepo extends JpaRepository<Admin, Integer> {


    //@Query("select g.adminId from Admin g where g.adminName=:adminName")
   // Optional<Admin> findByAdminName(String adminName);


    //Optional<Admin> findByAdminName(String adminName);
}
