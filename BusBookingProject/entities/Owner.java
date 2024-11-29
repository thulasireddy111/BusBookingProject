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
	@Column(nullable = false)
	private String owner_name;
	@Column(nullable = false)
	private Long mobile;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOwner_name() {
		return owner_name;
	}
	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}
	public Long getMobile() {
		return mobile;
	}
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	@Override
	public String toString() {
		return "Owner [id=" + id + ", owner_name=" + owner_name + ", mobile=" + mobile + "]";
	}
	public Owner(Long id, String owner_name, Long mobile) {
		super();
		this.id = id;
		this.owner_name = owner_name;
		this.mobile = mobile;
	}
	
	public Owner() {
		// TODO Auto-generated constructor stub
	}
	@OneToMany(mappedBy = "ownerId",cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonManagedReference
	List<Bus>bus=new ArrayList<>();
}
