package com.bus.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Owner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false,length = 20)
	private String name;
	@Column(nullable = false,unique = true,length = 255)
	private String mobileNo;
	@Override
	public String toString() {
		return "Owner [id=" + id + ", name=" + name + ", mobileNo=" + mobileNo + "]";
	}
	public Long getId() {
		return id;
	}
	public Owner(String name, String mobileNo) {
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
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	public Owner() {
		// TODO Auto-generated constructor stub
	}
	
	@OneToMany(mappedBy = "ownerId",orphanRemoval = true,cascade =CascadeType.ALL )
	@JsonManagedReference
	List<Bus>buses=new ArrayList<>();
	
//	@OneToMany(mappedBy = "ownerNo",orphanRemoval = true,cascade =CascadeType.ALL )
//	@JsonManagedReference
//	List<Driver>drivers=new ArrayList<>();
	
}
