package com.bus.dto;

public class PassengerDto {
	
	
	private Long passengerId;
	
	private String passengerName;
	
	private String email;
	public PassengerDto(String passengerName, String email, Long mobileNo) {
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
	
	private Long mobileNo;
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
	public Long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	public PassengerDto() {
		// TODO Auto-generated constructor stub
	}
	
	public Busdto getPassBus() {
		return passBus;
	}
	public void setPassBus(Busdto passBus) {
		this.passBus = passBus;
	}

	private Busdto passBus;

}
