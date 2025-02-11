package com.bus.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Seats {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookingId;
	@Column(nullable = false,length = 100,unique = true)
	private int seatNo;
	
	private boolean available = true;
	public Long getBookingId() {
		return bookingId;
	}
	@Override
	public String toString() {
		return "Seats [bookingId=" + bookingId + ", seatNo=" + seatNo + "]";
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	public int getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}
	public Seats(int seatNo) {
		super();
		this.seatNo = seatNo;
	}
	
	public Seats() {
		// TODO Auto-generated constructor stub
	}
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="busNo",nullable = false)
    private Bus busId;
	public Bus getBusId() {
		return busId;
	}
	public void setBusId(Bus bus) {
		this.busId = bus;
	}
	
	 public boolean isAvailable() {
	        return available; 
	    }

	    public void setAvailable(boolean available) {
	        this.available = available;
	    }

}
