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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bus.dto.ApiResponse;
import com.bus.entities.Owner;
import com.bus.service.OwnerService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/owner")
public class OwnerController {

	@Autowired
	public OwnerService ownerService;
	
	@PostMapping("/add")
	public ResponseEntity<?>addOwner(@RequestBody Owner owner){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ApiResponse(ownerService.addOwner(owner)));
	}
	@GetMapping("/get/{id}")
	public ResponseEntity<?>getOwner(@PathVariable Long id){
		return ResponseEntity.ok(ownerService.getOwner(id));
	}
	
	@GetMapping("/get")
	public ResponseEntity<?>getAllOwner(){
		return ResponseEntity.ok(ownerService.getAllOwners());
	}
	
	
	
	@GetMapping("/getAllOwnerBus/{id}")
	public ResponseEntity<?>getAllOwnerBus(@PathVariable Long id){
		return ResponseEntity.ok(ownerService.findAllOwnerAndBuses(id));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?>updateOwner(@RequestBody Owner owner,@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body((ownerService.updateOwner(id, owner)));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?>deleteOwner(@PathVariable Long id){
		return ResponseEntity.ok(ownerService.deleteOwner(id));
	}
	
	
	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String name, @RequestParam String mobileNo) {
        
        return ResponseEntity.ok( ownerService.loginOwner(name, mobileNo));
    }
}
