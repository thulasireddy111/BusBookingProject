package com.bus.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
public class Bus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bus_no;
	@Min(value = 1, message = "A bus must have at least 1 seat")
    @Max(value = 100, message = "A bus cannot have more than 100 seats")
	@NonNull
	private int seatsAvailable;
	@Column(nullable = false)
	private String src;
	@Column(nullable = false)
	private String dest;
	@Column(nullable = false)
	private LocalDate deptDate;
	@Column(nullable = false)
	private LocalDate arrDate;
	public Long getBus_no() {
		return bus_no;
	}
	public void setBus_no(Long bus_no) {
		this.bus_no = bus_no;
	}
	public int getSeatsAvailable() {
		return seatsAvailable;
	}
	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getDest() {
		return dest;
	}
	public void setDest(String dest) {
		this.dest = dest;
	}
	public LocalDate getDeptDate() {
		return deptDate;
	}
	public void setDeptDate(LocalDate deptDate) {
		this.deptDate = deptDate;
	}
	public LocalDate getArrDate() {
		return arrDate;
	}
	public void setArrDate(LocalDate arrDate) {
		this.arrDate = arrDate;
	}
	public Bus(Long bus_no,
			@Min(value = 1, message = "A bus must have at least 1 seat") @Max(value = 100, message = "A bus cannot have more than 100 seats") int seatsAvailable,
			String src, String dest, LocalDate deptDate, LocalDate arrDate) {
		super();
		this.bus_no = bus_no;
		this.seatsAvailable = seatsAvailable;
		this.src = src;
		this.dest = dest;
		this.deptDate = deptDate;
		this.arrDate = arrDate;
	}
	@Override
	public String toString() {
		return "Bus [bus_no=" + bus_no + ", seatsAvailable=" + seatsAvailable + ", src=" + src + ", dest=" + dest
				+ ", deptDate=" + deptDate + ", arrDate=" + arrDate + "]";
	}
	public Bus() {
		// TODO Auto-generated constructor stub
	}
	@ManyToOne
	@JoinColumn(name="ownerId",nullable = false)
	@JsonBackReference
	private Owner ownerId;
	
	@OneToMany(mappedBy = "busId",cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonManagedReference
	List<Passengers>passenger=new ArrayList<>();
}
