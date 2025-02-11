package com.bus.service;

import java.util.List;

import com.bus.dto.DriverDto;
import com.bus.entities.Bus;
import com.bus.entities.Driver;

public interface DriverService {
	
	String addDriver(Driver driver);
	DriverDto getDriver(Long DriverNo);
	DriverDto updateDriver(Long DriverNo,Driver driver);
	String deleteDriver(Long driverNo);
	List<Driver>getAllDrivers();
	List<Driver>getAllDrivers(Bus busNo);

}
