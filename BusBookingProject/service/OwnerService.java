package com.bus.service;

import com.bus.entities.Owner;

public interface OwnerService {
	String addOwner(Owner owner);
	String deleteOwner(Long id);
	String updateOwner(Long id,Owner newOwner);
	Owner getOwnerAndBus(Long id);
//	Owner getOwnerAndDriver(Long id);
}
