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
import com.bus.entities.Driver;
import com.bus.service.DriverService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/driver")
public class DriverController {
	public DriverController() {

		System.out.println("in driver controller "+getClass());
	}
	@Autowired
	public DriverService driverService;
	
	@PostMapping("/add")
	public ResponseEntity<?>addDriver(@RequestBody Driver driver){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ApiResponse(driverService.addDriver(driver)));
	}

	@GetMapping("/get/{driverNo}")
	public ResponseEntity<?>getDriver(@PathVariable Long driverNo){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(driverService.getDriver(driverNo));
				
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?>getAllDriver(){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(driverService.getAllDrivers());
				
	}
	@PutMapping("/update/{driverNo}")
	public ResponseEntity<?>updateDriver(@PathVariable Long driverNo,@RequestBody Driver driver){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(driverService.updateDriver(driverNo, driver));			
	}
	@DeleteMapping("/Delete/{driverNo}")
	public ResponseEntity<?>deleteDriver(@PathVariable Long driverNo){
		return ResponseEntity.status(HttpStatus.OK)
				.body(driverService.deleteDriver(driverNo));
				
	}
	
	@GetMapping("/getAllDriversByBusNo/{busNo}")
	
	public ResponseEntity<?>getAllDriversByBusNo(@PathVariable Bus busNo){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(driverService.getAllDrivers(busNo));
				
	}
	
}
