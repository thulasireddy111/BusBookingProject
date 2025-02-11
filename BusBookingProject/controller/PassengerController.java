package com.bus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus.entities.Passengers;
import com.bus.service.PassengerService;

@RestController
@RequestMapping("/pass")
public class PassengerController {
	@Autowired
	private PassengerService passService;
	
	@PostMapping("/add")
	public ResponseEntity<?>addPassenger(@RequestBody Passengers passenger){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body((passService.addPassenger(passenger)));
	}
//	@GetMapping("/get/{age}")
//	public ResponseEntity<?>getpassenger(@PathVariable int age){
//		return ResponseEntity.ok(passService.getPassenger(age));
//	}

}
