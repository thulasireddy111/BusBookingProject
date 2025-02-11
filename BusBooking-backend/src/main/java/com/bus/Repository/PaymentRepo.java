package com.bus.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bus.entities.Payment;
import com.bus.entities.Status;

public interface PaymentRepo extends JpaRepository<Payment,Long> {
	Optional<Payment> findByOrderId(String orderId);

	@Query("SELECT p FROM Payment p JOIN FETCH p.seat WHERE p.status != :status")
    List<Payment> findAllPaymentsWithSeat(@Param("status") Status status);
}
