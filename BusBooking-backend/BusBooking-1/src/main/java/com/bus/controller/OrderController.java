package com.bus.controller;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus.Repository.PassengerRepo;
import com.bus.Repository.PaymentRepo;
import com.bus.Repository.SeatsRepo;
import com.bus.entities.OrderRequest;
import com.bus.entities.Passengers;
import com.bus.entities.Payment;
import com.bus.entities.PaymentMethod;
import com.bus.entities.PaymentResponse;
import com.bus.entities.Seats;
import com.bus.entities.Status;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/passenger")
public class OrderController {
    
    @Autowired
    private PaymentRepo paymentRepository;

    @Autowired
    private SeatsRepo seatRepository;
    
    @Autowired
    private PassengerRepo passengerRepository;

    private static final String MOCK_PAYMENT_LINK = "https://mock-payment-link.com"; // Define the mock payment link

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request) {
        try {
            // Retrieve the passenger using passengerId from the request
            Optional<Passengers> passengerOpt = passengerRepository.findById(request.getPassengerId());
            if (!passengerOpt.isPresent()) {
                return ResponseEntity.badRequest().body("Passenger not found");
            }
            Passengers passenger = passengerOpt.get();

            // Retrieve the seat using seatId from the request
            Optional<Seats> seatOpt = seatRepository.findById(request.getSeatId());
            if (!seatOpt.isPresent()) {
                return ResponseEntity.badRequest().body("Seat not found");
            }
            Seats seat = seatOpt.get();

            // Check if the seat is available
            if (!seat.isAvailable()) {
                return ResponseEntity.badRequest().body("Seat is not available");
            }

            // Generate a unique order ID
            String orderId = UUID.randomUUID().toString().substring(0, 6);

            // Create a Payment object and link the seat
            Payment payment = new Payment(
                passenger, 
                request.getAmount(), 
                PaymentMethod.CREDIT_CARD, 
                LocalDate.now(), 
                Status.PENDING, 
                orderId,
                seat  // Set the seat for the payment
            );

            // Save the payment to the repository
            paymentRepository.save(payment);

            // Mark the seat as unavailable
            seat.setAvailable(false);
            seatRepository.save(seat);

            return ResponseEntity.ok("Order created successfully. Payment Link: " + MOCK_PAYMENT_LINK + "?orderId=" + orderId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to create order");
        }
    }


    @PostMapping("/payment-success")
    public ResponseEntity<?> paymentSuccess(@RequestBody PaymentResponse paymentResponse) {
        try {
            if (!verifySignature(paymentResponse)) {
                return ResponseEntity.status(400).body("Invalid signature");
            }

            Optional<Payment> paymentOpt = paymentRepository.findByOrderId(paymentResponse.getOrderId());
            if (paymentOpt.isPresent()) {
                Payment paymentDb = paymentOpt.get();
                paymentDb.setStatus(Status.COMPLETED);
                
                Seats seat = seatRepository.findById(paymentResponse.getSeatId()).orElseThrow();
                seat.setAvailable(false);
                seatRepository.save(seat);
                
                paymentRepository.save(paymentDb);
                return ResponseEntity.ok("Payment successful and seat booked!");
            }
            return ResponseEntity.status(404).body("Payment not found");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Payment verification failed");
        }
    }

    @DeleteMapping("/cancel-order")
    public ResponseEntity<?> cancelOrder(@RequestBody Map<String, String> requestBody) {
        try {
            String orderId = requestBody.get("orderId");
            
            Optional<Payment> paymentOpt = paymentRepository.findByOrderId(orderId);
            if (paymentOpt.isPresent()) {
                Payment payment = paymentOpt.get();
                
                // Set the seat as available
                if (payment.getSeat() != null) {
                    Seats seat = payment.getSeat();
                    seat.setAvailable(true);
                    seatRepository.save(seat);
                }

                // Delete the payment record (the entire order)
                paymentRepository.delete(payment);
                
                return ResponseEntity.ok("Order cancelled and seat is now available");
            }
            return ResponseEntity.status(404).body("Order not found");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to cancel order");
        }
    }

    private boolean verifySignature(PaymentResponse paymentResponse) {
        return true;
    }
    
    @GetMapping("/getAllOrders")
    public ResponseEntity<?> getAllOrders() {
       return ResponseEntity.ok(paymentRepository.findAll());
    }

}
