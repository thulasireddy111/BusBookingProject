package com.bus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bus.Repository.PassengerRepo;
import com.bus.Repository.PaymentRepo;
import com.bus.entities.Passengers;
import com.bus.entities.Payment;
import com.bus.entities.Status;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
    private PaymentRepo paymentRepository;

    @Autowired
    private PassengerRepo passengerRepository;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    public Payment createPayment(Payment payment,Long passengerId) {
        Passengers passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));
        payment.setPassenger(passenger);
        return paymentRepository.save(payment);
    }

    public Payment updatePaymentStatus(Long paymentId, Status status) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        payment.setStatus(status);
        return paymentRepository.save(payment);
    }

    public String deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new RuntimeException("Payment not found");
        }
        paymentRepository.deleteById(id);
        return "payment removed successfully";
    }

}
