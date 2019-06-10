package com.octopus.service.exception;

import javax.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.octopus.service.domain.ApiResponse;
import com.octopus.service.domain.Error;
import com.octopus.service.util.ErrorCode;
import com.octopus.service.util.ExceptionConstants;
import com.octopus.service.util.ExceptionResponse;
import com.octopus.service.util.MessageByLocale;


@RestControllerAdvice
public class GlobalCustomExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalCustomExceptionHandler.class);

	@Autowired
	private MessageByLocale messageByLocale;
	
	
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

	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	@ExceptionHandler
	public ResponseEntity<ApiResponse> handleSearchException(SearchException exception) {
		// handle exception if search parameters get invalid values
		ApiResponse responseBody = new ApiResponse();
		List<Error> errors = null;

		if (exception instanceof InvalidSearchTermValueException) {
			errors = Arrays
					.asList((InvalidSearchTermValueException) exception)
					.stream()
					.map(error -> {
						String[] errorArgs = {error.getTerm(), String.valueOf(error.getValue())};

						return new Error(ErrorCode.INVALID_ARGUMENT.name(), "search",
								messageByLocale.getMessage("error-message.invalid-search-term-value", errorArgs));
					})
					.collect(Collectors.toList());

		} else if (exception instanceof InvalidSearchTermException) {
			errors = Arrays
					.asList((InvalidSearchTermException) exception)
					.stream()
					.map(error -> {
						String[] errorArgs = {error.getTerm()};

						return new Error(ErrorCode.INVALID_ARGUMENT.name(), "search",
								messageByLocale.getMessage("error-message.invalid-search-term", errorArgs));
					})
					.collect(Collectors.toList());
		} else if (exception instanceof InvalidSearchOperatorException) {
			errors = Arrays
					.asList((InvalidSearchOperatorException) exception)
					.stream()
					.map(error -> {
						String[] errorArgs = {error.getTerm(), error.getOperator()};

						return new Error(ErrorCode.INVALID_ARGUMENT.name(), "search",
								messageByLocale.getMessage("error-message.invalid-search-operator", errorArgs));
					})
					.collect(Collectors.toList());
		}

		Error error = new Error(ErrorCode.BAD_REQUEST.name(), messageByLocale.getMessage("error-message.bad-request"));
		error.setDetails(errors);

		responseBody.setError(error);

		LOGGER.warn("Invalid search query", exception);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(responseBody);
	}
}
