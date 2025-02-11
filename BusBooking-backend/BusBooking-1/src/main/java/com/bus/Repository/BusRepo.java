package com.bus.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bus.entities.Bus;
import com.bus.entities.Driver;
import com.bus.entities.Passengers;

@Repository
public interface BusRepo extends JpaRepository<Bus, Long> {

	@Query("select o from Bus o left join fetch o.drivers where o.id=:id")
	List<Driver> getOwnerAndDriver(Long id);
	
	
	@Query("select o from Bus o left join fetch o.passengers where o.id=:id")
	List<Bus> getBusAndPassengers(Long id);
}
