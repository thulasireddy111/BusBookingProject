package com.bus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bus.entities.Bus;
import com.bus.entities.Seats;
import com.bus.service.PassengerService;
import com.bus.service.SeatsService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/seats")
public class SeatsController {
	
	 @Autowired
	    private PassengerService passengerService;
	 
	 @Autowired
	 private SeatsService seatsService;

	 @GetMapping("/available")
	    public List<Seats> getAvailableSeats(@RequestParam Long busId) {
	        Bus bus = new Bus(); // Create a new bus object with the given busId
	        bus.setBusNo(busId); // Set the bus ID for querying
	        
	        return seatsService.getAvailableSeatsForBus(bus); // Fetch and return available seats for the bus
	    }
	 
	 
	 
	 
	    
	    
	    @PostMapping("/addSeat")
	    public ResponseEntity<?> addSeats(@RequestBody Seats seats) {
	        return  ResponseEntity.status(HttpStatus.CREATED).body(seatsService.addSeats(seats));
	    }
	    
	    
	    
	    @PostMapping("/book")
	    public ResponseEntity<String> bookSeat(
	            @RequestParam Long passengerId,
	            @RequestParam int seatNo) { 
	        String responseMessage = seatsService.bookSeat(passengerId, seatNo);
	        return ResponseEntity.ok(responseMessage);
	    }

}
