package com.bus.service;

import java.util.List;

import com.bus.entities.Bus;

public interface BusService {
	String addBus(Bus bus);
	List<Bus>getBus();
	String getById(Long id);
	String updateBus(Bus newBus,Long id);
	String deleteBusById(Long id);

}
