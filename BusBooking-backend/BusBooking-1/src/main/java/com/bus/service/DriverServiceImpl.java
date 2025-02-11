package com.bus.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bus.Repository.DriverRepo;
import com.bus.custom_Exceptions.ResourceNotFoundException;
import com.bus.dto.DriverDto;
import com.bus.entities.Bus;
import com.bus.entities.Driver;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {
	
	@Autowired
	public DriverRepo driverRepo;
	
	@Autowired
	public ModelMapper modelMapper;

	@Override
	public String addDriver(Driver driver) {
		driverRepo.save(driver);
		
		return "driver added successfully with id "+driver.getDriverId();
	}

	@Override
	public DriverDto getDriver(Long DriverNo) {
		Driver entity=driverRepo.findById(DriverNo).orElseThrow
		(()->new ResourceNotFoundException
				("Invalid Id please enter valid id!!!"));
		return modelMapper.map(entity, DriverDto.class);
	}

	@Override
	public DriverDto updateDriver(Long DriverNo, Driver driver) {
		Driver existingDriver = driverRepo.findById(DriverNo)
	            .orElseThrow(() -> new ResourceNotFoundException("Driver not found with id: " + DriverNo));
		existingDriver.setDriverName(driver.getDriverName()); 
		existingDriver.setDriverNo(driver.getDriverNo());
	    Driver updatedOwner = driverRepo.save(existingDriver);
	    return modelMapper.map(updatedOwner, DriverDto.class);
	}

	@Override
	public String deleteDriver(Long driverNo) {
		driverRepo.deleteById(driverNo);
		return "driver deleted successfully "+driverNo;
	}

	@Override
	public List<Driver> getAllDrivers() {
		List<Driver> drivers = driverRepo.findAll();
		return drivers;
	}

	@Override
	public List<Driver> getAllDrivers(Bus busNo) {
		
		return driverRepo.findByBusNo(busNo);
	}

}
