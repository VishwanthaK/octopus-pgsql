package com.octopus.service.exception;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.octopus.service.domain.ApiResponse;
import com.octopus.service.domain.Error;
import com.octopus.service.util.ExceptionConstants;
import com.octopus.service.util.ExceptionResponse;


@RestControllerAdvice
public class GlobalCustomExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalCustomExceptionHandler.class);
	
	
	@ResponseStatus(value=HttpStatus.EXPECTATION_FAILED)
	@ExceptionHandler(OctopusPermissionException.class)
	public ExceptionResponse handleOctopusPermissionException(HttpServletRequest request, Exception ex) {
		LOGGER.error("OCTOPUS PERMISSION EXCEPTION OCCURED :: URL = "+request.getRequestURL());
		LOGGER.error("EXCEPTION :: "+ex);
		ex.printStackTrace();
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				ExceptionConstants.OCTOPUS_PERMISSION_EXCEPTION_CODE, 
				ex.getMessage());
		return exceptionResponse;
	}

	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BadRequestexception.class)
	public ResponseEntity<ApiResponse> handleBadRequestException(HttpServletRequest request, BadRequestexception ex) {
		LOGGER.error("BAD REQUEST EXCEPTION OCCURED :: URL = "+request.getRequestURL());
		LOGGER.error("EXCEPTION :: "+ex);
		ex.printStackTrace();

		Error error = new Error(String.valueOf(HttpStatus.BAD_REQUEST.value()), "Request is invalid");
		List<Error> errorDetails = ex.getErrors();

		if (errorDetails != null && !errorDetails.isEmpty()) {
			error.setDetails(errorDetails);
		}

		ApiResponse response = new ApiResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), error);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(response);
	}
}
