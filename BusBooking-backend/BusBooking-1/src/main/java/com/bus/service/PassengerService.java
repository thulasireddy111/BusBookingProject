package com.bus.service;

import java.util.List;

import com.bus.entities.Bus;
import com.bus.entities.Passengers;
import com.bus.entities.Seats;

public interface PassengerService {
	
	String addPassenger(Passengers passeger);
	//String getPassenger(int age);
	List<Bus>getAllBuses();
	//List<Bus> getBusDetails(int busNo);
	List<Seats> getAllSeats(Long busNo);
	String bookSeatForPassenger(Long seatId, Long passengerId);
	Passengers login(String email,String mobileNo);
	List<Passengers>getAllPass();

}
