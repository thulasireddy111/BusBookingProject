package com.bus.dto;

import com.bus.entities.Bus;
import com.bus.entities.Owner;

public class DriverDto {
	
	
	private Long driverId;
	
	private String driverName;
	public DriverDto(String driverName, Long driverNo) {
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
	
	private Long driverNo;
	

	public DriverDto() {
		// TODO Auto-generated constructor stub
	}
	
	
	private Bus busNo;
	public Bus getBusNo() {
		return busNo;
	}
	public void setBusNo(Bus busNo) {
		this.busNo = busNo;
	}
	
	
	

}
