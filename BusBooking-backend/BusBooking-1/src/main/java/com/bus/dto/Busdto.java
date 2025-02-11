package com.bus.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.bus.entities.Driver;
import com.bus.entities.Passengers;
import com.bus.entities.Seats;


public class Busdto {

	
	public Long busNo;
	
	public String busName;
	
	public String arrivalCity;
	
	public String departureCity;
	
	public LocalDate arrivalDate;
	
	public LocalDate departureDate;
	
	
	public LocalTime arrivalTime;
	
	public LocalTime departureTime;
	
	public Busdto(String busName, String arrivalCity, String departureCity, LocalDate arrivalDate,
			LocalDate departureDate,LocalTime arrivalTime,LocalTime departureTime) {
		super();
		this.busName = busName;
		this.arrivalCity = arrivalCity;
		this.departureCity = departureCity;
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
		this.arrivalTime=arrivalTime;
		this.departureTime=departureTime;
	}
	
	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	@Override
	public String toString() {
		return "Bus [busNo=" + busNo + ", busName=" + busName + ", arrivalCity=" + arrivalCity + ", departureCity="
				+ departureCity + ", arrivalDate=" + arrivalDate + ", departureDate=" + departureDate + ", arrivalTime="
				+ arrivalTime + ", departureTime=" + departureTime + "]";
	}

	public Long getBusNo() {
		return busNo;
	}
	public void setBusNo(Long busNo) {
		this.busNo = busNo;
	}
	public String getBusName() {
		return busName;
	}
	public void setBusName(String busName) {
		this.busName = busName;
	}
	public String getArrivalCity() {
		return arrivalCity;
	}
	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}
	public String getDepartureCity() {
		return departureCity;
	}
	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}
	public LocalDate getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public LocalDate getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}
	
	
	private OwnerDto ownerId;
	
	public OwnerDto getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(OwnerDto ownerId) {
		this.ownerId = ownerId;
	}
	public Busdto() {
		// TODO Auto-generated constructor stub
	}
	
	
	List<Driver>drivers=new ArrayList<>();
	
	
	List<Passengers>passengers=new ArrayList<>();
	
	
	
	List<Seats>seats=new ArrayList<>();
	
	
	
}
