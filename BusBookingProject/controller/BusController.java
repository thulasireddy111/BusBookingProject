package com.bus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus.entities.Bus;
import com.bus.service.BusService;



@RestController
@RequestMapping("/bus")
public class BusController {
	@Autowired
	private BusService busService;
	
	@PostMapping
	public ResponseEntity<?>addBus(@RequestBody Bus newBus){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body((busService.addBus(newBus)));
	}
	@GetMapping("/get")
		public List<Bus>getBus(){
			return busService.getBus();
		}
	@GetMapping("/get/{id}")
	public ResponseEntity<?>getBusById(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(busService.getById(id));
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<?>updateBusDetails(@RequestBody Bus bus,@PathVariable Long id){
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(busService.updateBus(bus, id));
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?>deleteById(@PathVariable Long id){
		
		return ResponseEntity.status(HttpStatus.OK)
				.body((busService.deleteBusById(id)));
	}
	
	
	
}


