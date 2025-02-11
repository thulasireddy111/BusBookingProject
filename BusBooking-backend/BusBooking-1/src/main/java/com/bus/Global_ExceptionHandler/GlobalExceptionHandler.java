package com.bus.Global_ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bus.custom_Exceptions.AuthenticationException;
import com.bus.custom_Exceptions.ResourceNotFoundException;
import com.bus.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public ApiResponse handleResourceNotFoundException(ResourceNotFoundException e) {
		System.out.println("in resource not found ");
		return new ApiResponse(e.getMessage());
	}
	@ExceptionHandler(AuthenticationException.class)
	@ResponseStatus(value=HttpStatus.UNAUTHORIZED)
	public ApiResponse handleAuthenticationException(AuthenticationException e) {
		return new ApiResponse(e.getMessage());
	}

}
