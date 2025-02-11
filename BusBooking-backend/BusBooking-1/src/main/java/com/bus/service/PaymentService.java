package com.bus.service;

import java.util.List;

import com.bus.entities.Payment;
import com.bus.entities.Status;

public interface PaymentService {
	List<Payment> getAllPayments();
	Payment getPaymentById(Long id);
	 Payment createPayment(Payment payment,Long passengerId);
	 Payment updatePaymentStatus(Long paymentId, Status status);
	 String deletePayment(Long id);
	 
}
