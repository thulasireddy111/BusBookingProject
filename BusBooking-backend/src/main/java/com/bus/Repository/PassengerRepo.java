package com.bus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bus.entities.Passengers;

public interface PassengerRepo extends JpaRepository<Passengers, Long> {
	//List<Seats>findAllPassBusAndSeatNo();
	Passengers findByEmailAndMobileNo(String email, String mobileNo);
}
