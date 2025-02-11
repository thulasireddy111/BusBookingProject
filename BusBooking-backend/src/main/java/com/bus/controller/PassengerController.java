package com.bus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bus.entities.Passengers;
import com.bus.service.PassengerService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/passenger")
public class PassengerController {
	
	@Autowired
	public PassengerService passService;
	
	@PostMapping("/addPassenger")
    public ResponseEntity<?> addPassenger(@RequestBody Passengers passeneger) {
        return ResponseEntity.ok(passService.addPassenger(passeneger));
    }

	
	 @GetMapping("/getAllBuses")
	    public ResponseEntity<?> getPassenger() {
	        return ResponseEntity.ok(passService.getAllBuses());
	    }
	 @GetMapping("/getAllSeats/{busNo}")
	    public ResponseEntity<?> getSeats(@PathVariable Long busNo) {
	        return ResponseEntity.ok(passService.getAllSeats(busNo));
	    }
	 
	 
	 @GetMapping("/getAllPassengers")
	    public ResponseEntity<?> getAllPassengers() {
	        return ResponseEntity.ok(passService.getAllPass());
	    }
	 
	 
	 @PostMapping("/book-seat")
	    public String bookSeat(@RequestParam Long seatId, @RequestParam Long passengerId) {
	        return passService.bookSeatForPassenger(seatId, passengerId);
	    }
	 
	 @PostMapping("/login")
	    public ResponseEntity<?> getSeats(@RequestBody Passengers passenger) {
		 
		     Passengers validatedPassenger = passService.login(passenger.getEmail(), passenger.getMobileNo());
		     
		     if (validatedPassenger != null) {
		         return ResponseEntity.ok(validatedPassenger);
		     } else {
		         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or mobile number");
		     }
		 }

	    }
