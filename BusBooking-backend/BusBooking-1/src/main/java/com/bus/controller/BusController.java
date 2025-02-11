package com.bus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus.dto.ApiResponse;
import com.bus.entities.Bus;
import com.bus.service.BusService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/bus")
public class BusController {
	
	public BusController() {

		System.out.println("in bus controller "+getClass());
	}
	@Autowired
	public BusService busService;

	@PostMapping("/add")
	public ResponseEntity<?>addBus(@RequestBody Bus bus){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ApiResponse(busService.addBus(bus)));
	}
	@GetMapping("/get/{busNo}")
	public ResponseEntity<?>getBus(@PathVariable Long busNo){
		return ResponseEntity.ok(busService.getBus(busNo));
	}
	
	@GetMapping("/get")
	public ResponseEntity<?>getAllBus(){
		return ResponseEntity.ok(busService.getAllBus());
	}
	
	@PutMapping("/update/{busNo}")
	public ResponseEntity<?>updateBus(@RequestBody Bus bus,@PathVariable Long busNo){
		return ResponseEntity.status(HttpStatus.OK)
				.body((busService.updateBus(busNo, bus)));
	}

	@DeleteMapping("/delete/{busNo}")
	public ResponseEntity<?>deleteOwner(@PathVariable Long busNo){
		return ResponseEntity.ok(busService.deleteBus(busNo));
	}
	
	@GetMapping("/getBusPass/{busNo}")
	public ResponseEntity<?>getBusAndPassenger(@PathVariable Long busNo){
		return ResponseEntity.ok(busService.getAllBusAndPassengers(busNo));
	}

}
