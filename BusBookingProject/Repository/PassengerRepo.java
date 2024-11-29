package com.bus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bus.entities.Passengers;

@Repository
public interface PassengerRepo extends JpaRepository<Passengers, Long> {
	//Passengers findByAge(int age);
}
