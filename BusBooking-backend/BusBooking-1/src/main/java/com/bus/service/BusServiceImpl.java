package com.bus.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bus.Repository.BusRepo;
import com.bus.custom_Exceptions.ResourceNotFoundException;
import com.bus.dto.Busdto;
import com.bus.entities.Bus;

@Service
@Transactional
public class BusServiceImpl implements BusService{
	
	@Autowired
	public BusRepo busRepo;
	
	@Autowired
	public ModelMapper modelMapper;

	@Override
	public String addBus(Bus bus) {
		busRepo.save(bus);
		return "bus added successfully with No "+bus.getBusNo();
	}

	@Override
	public Bus getBus(Long busNo) {
		return busRepo.findById(busNo)
				.orElseThrow(()->new ResourceNotFoundException
						("Bus no is not valid Please enter valid No!!!"));
	}

	public Bus updateBus(Long busNo, Bus bus) {
	    // Check if the bus exists
	    Optional<Bus> existingBus = busRepo.findById(busNo);
	    if (!existingBus.isPresent()) {
	        throw new ResourceNotFoundException("Bus not found with busNo: " + busNo);
	    }

	    // Update fields
	    Bus updatedBus = existingBus.get();
	    updatedBus.setBusName(bus.getBusName());
	    updatedBus.setArrivalCity(bus.getArrivalCity());
	    updatedBus.setDepartureCity(bus.getDepartureCity());
	    updatedBus.setArrivalDate(bus.getArrivalDate());
	    updatedBus.setDepartureDate(bus.getDepartureDate());
	    updatedBus.setArrivalTime(bus.getArrivalTime());
	    updatedBus.setDepartureTime(bus.getDepartureTime());

	    return busRepo.save(updatedBus); // Save updated bus to DB
	}


	@Override
	public String deleteBus(Long busNo) {
		busRepo.deleteById(busNo);
		return "bus with busNo "+busNo+" deleted successfully";
	}

	@Override
	public List<Bus> getAllBus() {
		
		return busRepo.findAll();
	}

	@Override
	public List<Bus> getAllBusAndPassengers(Long busNo) {
		
		return busRepo.getBusAndPassengers(busNo);
	}

}
