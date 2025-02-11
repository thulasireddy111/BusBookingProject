package com.bus.dto;

import java.time.LocalDate;

import com.bus.entities.Passengers;
import com.bus.entities.PaymentMethod;
import com.bus.entities.Status;

public class PaymentDto {
	


    private Long id;

   
    private Passengers passengers;

  
    private Double amount;

    
    private PaymentMethod paymentMethod; // e.g., "Credit_Card", "UPI", "Cash"

  
    private LocalDate paymentDate;

   
    private Status status; // e.g., "Completed", "Pending", "Failed"

    public PaymentDto() {
    }

    public PaymentDto(Passengers passenger, Double amount, PaymentMethod paymentMethod, LocalDate paymentDate, Status status) {
    	this.passengers=passenger;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Passengers getPassenger() {
        return passengers;
    }

    public void setPassenger(Passengers passenger) {
        this.passengers = passenger;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
