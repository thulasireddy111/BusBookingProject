package com.bus.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Passengers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passengerId;

    @Column(nullable = false, length = 20)
    private String passengerName;

    @Column(nullable = false, unique = true, length = 20)
    private String email;

    @Column(nullable = false, unique = true, length = 255)
    private String mobileNo;

    // New fields for OTP functionality
    @Column(nullable = true)
    private String otp;  // Store the OTP

    @Column(nullable = true)
    private LocalDateTime otpGeneratedTime; // Store when OTP was generated

    // Default constructor
    public Passengers() {
        // TODO Auto-generated constructor stub
    }

    // Constructor with essential fields
    public Passengers(String passengerName, String email, String mobileNo) {
        super();
        this.passengerName = passengerName;
        this.email = email;
        this.mobileNo = mobileNo;
    }

    @Override
    public String toString() {
        return "Passengers [passengerId=" + passengerId + ", passengerName=" + passengerName + ", email=" + email
                + ", mobileNo=" + mobileNo + "]";
    }

    // Getters and setters
    public Long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Bus getPassBus() {
        return passBus;
    }

    public void setPassBus(Bus passBus) {
        this.passBus = passBus;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getOtpGeneratedTime() {
        return otpGeneratedTime;
    }

    public void setOtpGeneratedTime(LocalDateTime otpGeneratedTime) {
        this.otpGeneratedTime = otpGeneratedTime;
    }

    public Seats getSeat() {
        return seat;
    }

    public void setSeat(Seats seat) {
        this.seat = seat;
    }

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "busNo", nullable = false)
    private Bus passBus;

    @OneToOne
    @JoinColumn(name = "seatId")
    private Seats seat;

    @OneToMany(mappedBy = "passengers", cascade = CascadeType.ALL)
    private List<Payment> payments;

    // Utility method to generate OTP (can be moved to service layer)
    public void generateOtp() {
        // Generate a 6-digit random OTP
        this.otp = String.format("%06d", new Random().nextInt(999999));
        this.otpGeneratedTime = LocalDateTime.now(); // Set the OTP generation time
    }
}
