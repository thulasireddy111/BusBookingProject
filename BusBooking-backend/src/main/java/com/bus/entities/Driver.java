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
public class Driver {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long driverId;
	@Column(nullable = false,length = 20)
	private String driverName;
	@Column(nullable = false,length = 10,unique = true)
	private Long driverNo;
	public Driver(String driverName, Long driverNo) {
		super();
		this.driverName = driverName;
		this.driverNo = driverNo;
	}
	@Override
	public String toString() {
		return "Driver [driverId=" + driverId + ", driverName=" + driverName + ", driverNo=" + driverNo + "]";
	}
	public Long getDriverId() {
		return driverId;
	}
	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public Long getDriverNo() {
		return driverNo;
	}
	public void setDriverNo(Long driverNo) {
		this.driverNo = driverNo;
	}
	
	

	public Driver() {
		// TODO Auto-generated constructor stub
	}
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="busNo",nullable = false)
	private Bus busNo;
	public Bus getBusNo() {
		return busNo;
	}
	public void setBusNo(Bus busNo) {
		this.busNo = busNo;
	}

}
