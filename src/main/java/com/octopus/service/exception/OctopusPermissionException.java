package com.octopus.service.exception;

public class OctopusPermissionException extends RuntimeException  {
	
	private static final long serialVersionUID = 8640656852777031507L;
	private String message;

	public OctopusPermissionException() {
		super();
	}

	public OctopusPermissionException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
