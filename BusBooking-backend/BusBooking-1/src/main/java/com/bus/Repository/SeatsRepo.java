package com.bus.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bus.entities.Bus;
import com.bus.entities.Seats;

public interface SeatsRepo extends JpaRepository<Seats, Long> {
      Seats findBySeatNo(int seatNo);
      
      List<Seats> findByBusIdAndAvailableTrue(Bus busId);
}
