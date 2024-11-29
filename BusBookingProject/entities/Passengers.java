package com.bus.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Passengers {
	@Id
	@Column(nullable = false,length = 10,unique = true)
	private Long mobile_no;
	@Column(nullable = false)
	private String pName;
	private int age;
	@Column(unique = true,nullable = false)
	private String email;
	public Long getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(Long mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Passengers [mobile_no=" + mobile_no + ", pName=" + pName + ", age=" + age + ", email=" + email + "]";
	}
	public Passengers(Long mobile_no, String pName, int age, String email) {
		super();
		this.mobile_no = mobile_no;
		this.pName = pName;
		this.age = age;
		this.email = email;
	}
	
	
   public Passengers() {
	// TODO Auto-generated constructor stub
   }
	@ManyToOne
	@JoinColumn(name="busId",nullable = false)
	@JsonBackReference
	private Bus busId;
}
