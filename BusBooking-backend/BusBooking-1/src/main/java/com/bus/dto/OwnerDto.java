package com.bus.dto;

import java.util.ArrayList;
import java.util.List;

public class OwnerDto {
	
	private Long id;
	
	private String name;

	private Long mobileNo;
	@Override
	public String toString() {
		return "Owner [id=" + id + ", name=" + name + ", mobileNo=" + mobileNo + "]";
	}
	public Long getId() {
		return id;
	}
	public OwnerDto(String name, Long mobileNo) {
		super();
		this.name = name;
		this.mobileNo = mobileNo;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public OwnerDto() {
		// TODO Auto-generated constructor stub
	}
	
	List<Busdto>buses=new ArrayList<>();
	
}
