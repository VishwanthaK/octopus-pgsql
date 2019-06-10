package com.octopus.service.domain;

public class ApiResponse {
	
	
	private Integer code;
	private String message;
	private Object data;
	private Error error;

	public ApiResponse() {}

	public ApiResponse(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public ApiResponse(Integer code, String message, Error error) {
		this.code = code;
		this.message = message;
		this.error = error;
	}

	public ApiResponse(Integer code, Object data) {
		this.code = code;
		this.data = data;
	}
	
	
	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public Error getError() {
		return error;
	}

	public Object getData() {
		return data;
	}

	public void setError(Error error) {
		this.error = error;
	}
}

