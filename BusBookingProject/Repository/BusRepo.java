package com.bus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bus.entities.Bus;
import com.bus.entities.Owner;
@Repository
public interface BusRepo extends JpaRepository<Bus, Long>{
	
}
