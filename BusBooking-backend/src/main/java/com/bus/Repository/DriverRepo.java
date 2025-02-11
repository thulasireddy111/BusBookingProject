package com.bus.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bus.entities.Bus;
import com.bus.entities.Driver;

public interface DriverRepo extends JpaRepository<Driver, Long> {

	List<Driver> findByBusNo(Bus busNo);
	
}
