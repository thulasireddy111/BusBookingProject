package com.bus.service;

import java.util.List;

import com.bus.entities.Bus;
import com.bus.entities.Seats;

public interface SeatsService {
	
	//Passengers addPassengerToSeat(String passengerName,int seatNo);
	
	Seats addSeats(Seats seats);
	
	String bookSeat(Long passengerId, int seatNo);

	List<Seats> getAvailableSeatsForBus(Bus bus);

}
