package com.octopus.service.exception;

import java.io.Serializable;

public class ExceptionResponse implements Serializable {
	
	private static final long serialVersionUID = 5299096808961968410L;
	
	private int code;
	private String message;
	
	public ExceptionResponse(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	
	
	public int getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	
	
}
