package com.bus.dto;

import java.time.LocalDateTime;

public class ApiResponse {
	private String message;
	private LocalDateTime timestamp;
	public ApiResponse(String message) {
		super();
		this.message = message;
		timestamp=LocalDateTime.now();
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public ApiResponse() {
		// TODO Auto-generated constructor stub
	}
	
}
