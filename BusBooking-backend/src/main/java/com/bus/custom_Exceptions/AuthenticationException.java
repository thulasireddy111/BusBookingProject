package com.bus.custom_Exceptions;

public class AuthenticationException extends RuntimeException{
	public AuthenticationException(String mesg) {
		super(mesg);
	}
}
