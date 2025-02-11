package com.bus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.Repository.BusRepo;
import com.bus.entities.Bus;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BusServiceImpl implements BusService {
	@Autowired
	private BusRepo busRepo;
//	@Autowired
//	private ModelMapper modelMapper;

	@Override
	public String addBus(Bus bus) {
		Bus newBus=busRepo.save(bus);
		return "bus added successfully ";
	}

	@Override
	public List<Bus> getBus() {
		
		return busRepo.findAll();
	}

	@Override
	public String getById(Long id) {
		Bus bus=busRepo.findById(id).orElseThrow(()->new RuntimeException());
		return "retrieved successfully";
	}

	@Override
	public String updateBus(Bus newBus,Long id) {
		Bus bus=busRepo.findById(id).orElse(null);
		if(busRepo.existsById(id)) {
			bus.setSrc(newBus.getSrc());
			bus.setDest(newBus.getDest());
			return "updated successfully";
		}
		
		throw new RuntimeException("invalid id");
	}

	@Override
	public String deleteBusById(Long id) {
		
		if(busRepo.existsById(id)) {
			busRepo.deleteById(id);
			return "deleted bus with id "+id+" successfully";
		}
		return "invalid id please enter valid id";
	}

}
