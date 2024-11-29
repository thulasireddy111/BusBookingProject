package com.bus.controller;

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

import com.bus.entities.Owner;
import com.bus.service.OwnerService;

@RestController
@RequestMapping("/owner")
public class OwnerController {
	@Autowired
	private OwnerService ownerService;
	@PostMapping
	public ResponseEntity<?> addOwner(@RequestBody Owner owner){
		return ResponseEntity.status(HttpStatus.CREATED).body(ownerService.addOwner(owner));
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?>deleteOwner(@PathVariable Long id){
		return ResponseEntity.ok((ownerService.deleteOwner(id)));
	}
	@GetMapping("/{id}")
	public ResponseEntity<?>getOwnerBus(@PathVariable Long id){
		return ResponseEntity.ok((ownerService.getOwnerAndBus(id)));
	}
//	@GetMapping("/driver/{id}")
//	public ResponseEntity<?>getOwnerDriver(@PathVariable Long id){
//		return ResponseEntity.ok(ownerService.getOwnerAndDriver(id));
//	}
	@PutMapping("/update/{id}")
	public ResponseEntity<?>updateOwner(@RequestBody Owner owner,@PathVariable Long id){
		return ResponseEntity.ok((ownerService.updateOwner(id, owner)));
	}
}
