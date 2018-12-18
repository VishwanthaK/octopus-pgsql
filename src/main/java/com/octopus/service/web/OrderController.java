package com.octopus.service.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.octopus.service.domain.SuccessResponse;
import com.octopus.service.service.OrderService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
	
	private SuccessResponse response = null;
	
	@Value("${token.header}")
    private String tokenHeader;
	
	@Autowired
	private OrderService orderService;


	@PreAuthorize("hasRole('ROLE_USER')")
	//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@RequestMapping(method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> makeAnOrder(HttpServletRequest request)
			throws JsonProcessingException {
		String token = request.getHeader(tokenHeader);
		String items = orderService.orderHistory();
		return new ResponseEntity<String>(items, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@RequestMapping(method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> cancelAnOrder(HttpServletRequest request,
			@RequestParam(value = "date", required = true) String date,
			Pageable pageable) throws JsonProcessingException {
		String token = request.getHeader(tokenHeader);
		String items = orderService.orderHistory();
		return new ResponseEntity<String>(items, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@RequestMapping( value = "/history",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getOrderHistory(HttpServletRequest request,
			@RequestParam(value = "date", required = true) String date,
			Pageable pageable) throws JsonProcessingException {
		String token = request.getHeader(tokenHeader);
		String items = orderService.orderHistory();
		return new ResponseEntity<String>(items, HttpStatus.OK);
	}


	
	

}
