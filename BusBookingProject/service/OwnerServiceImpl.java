package com.bus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.Repository.OwnerRepo;
import com.bus.entities.Owner;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class OwnerServiceImpl implements OwnerService {
	
	@Autowired
	private OwnerRepo ownerRepo;

	@Override
	public String addOwner(Owner owner) {
		Owner newOwner= ownerRepo.save(owner);
		
		return "Owner Added Successfully ";
	}

	@Override
	public String deleteOwner(Long id) {
		if(ownerRepo.existsById(id)) {
		ownerRepo.deleteById(id);
		return "owner deleted successffully";
		}
		return "owner with id not found please enter valid id!!!";
	}

	@Override
	public Owner getOwnerAndBus(Long id) {
			
		return ownerRepo.getOwnerBusDetails(id);
		
		
	}

//	@Override
//	public Owner getOwnerAndDriver(Long id) {
//		
//		return ownerRepo.getOwnerAndDriver(id);
//	}

	@Override
	public String updateOwner(Long id, Owner newOwner) {
		Owner owner=ownerRepo.findById(id).orElse(null);
		if(ownerRepo.existsById(id)) {
			owner.setOwner_name(newOwner.getOwner_name());
			return "Owner updated successfully ";
		}
		throw new RuntimeException("invalid Owner details");
	}

}
