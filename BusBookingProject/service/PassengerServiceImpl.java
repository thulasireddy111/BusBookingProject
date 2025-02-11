package com.bus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bus.Repository.PassengerRepo;
import com.bus.entities.Passengers;
@Service
@Transactional
public class PassengerServiceImpl implements PassengerService{
	
	@Autowired
	private PassengerRepo passengerRepo;
//	@Autowired
//	private ModelMapper modelMapper;

	@Override
	public String addPassenger(Passengers passegers) {
		Passengers passenger=passengerRepo.save(passegers);
		return "passenger added successfully "+passegers.getpName();
	}

//	@Override
//	public String getPassenger(int age) {
//		Passengers entity=passengerRepo.findByAge(age);
//		
//		return "passenger retrived successfully";
//	}

}
