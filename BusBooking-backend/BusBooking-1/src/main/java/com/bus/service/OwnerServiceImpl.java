package com.bus.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bus.Repository.OwnerRepo;
import com.bus.custom_Exceptions.ResourceNotFoundException;
import com.bus.dto.OwnerDto;
import com.bus.entities.Owner;
@Service
@Transactional
public class OwnerServiceImpl implements OwnerService {
	
	@Autowired
	public OwnerRepo ownerRepo;
	@Autowired
	public ModelMapper modelMapper;

	@Override
	public String addOwner(Owner owner) {
		ownerRepo.save(owner);
		return "owner added successfully with id "+owner.getId();
	}

	@Override
	public OwnerDto getOwner(Long id) {
	    Owner entity = ownerRepo.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Invalid id"));
	    return modelMapper.map(entity, OwnerDto.class);
	}
	
	@Override
	public OwnerDto updateOwner(Long id, Owner owner) {
	   
	    Owner existingOwner = ownerRepo.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Owner not found with id: " + id));
	    existingOwner.setName(owner.getName()); 
	    existingOwner.setMobileNo(owner.getMobileNo());
	    Owner updatedOwner = ownerRepo.save(existingOwner);
	    return modelMapper.map(updatedOwner, OwnerDto.class);
	}
	@Override
	public String deleteOwner(Long id) {
		ownerRepo.deleteById(id);
		return "Owner deleted successfully "+id;
	}

	@Override
	public List<Owner> getAllOwners() {
	    // Retrieve all Owner entities from the repository
	    return ownerRepo.findAll();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Owner> findAllOwnerAndBuses(Long id) {
		
		return (List<Owner>) ownerRepo.getOwnerBusDetails(id);
	}

	@Override
	public Optional<Owner> loginOwner(String name, String mobileNo) {
	    // Fetch owner from the database using the provided name and mobileNo
	    Optional<Owner> owner = ownerRepo.findByNameAndMobileNo(name, mobileNo);  // Make sure this query matches the data
	    if (owner == null) {
	        throw new ResourceNotFoundException("Invalid credentials");
	    }
	    return owner;
	}


	 
	
}
