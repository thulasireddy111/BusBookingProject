package com.bus.dto;

import com.bus.entities.Bus;

public class SeatsDto {
	
	
	private Long bookingId;
	
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
	public SeatsDto(int seatNo) {
		super();
		this.seatNo = seatNo;
	}
	
	public SeatsDto() {
		// TODO Auto-generated constructor stub
	}
	
	
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
