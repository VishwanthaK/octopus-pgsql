package com.octopus.service.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.octopus.service.domain.ApiResponse;
import com.octopus.service.dto.OrderData;
import com.octopus.service.service.OrderService;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
	
	private ApiResponse response = null;
	
	@Value("${token.header}")
    private String tokenHeader;
	@Autowired
	private OrderService orderService;


	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> makeAnOrder(HttpServletRequest request,
			@RequestBody OrderData orderDetails)
			throws JsonProcessingException {
		String token = request.getHeader(tokenHeader);
		response = orderService.makeAnOrder(token, orderDetails);

		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping( value = "/history",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> getOrderHistory(HttpServletRequest request,
		   	@RequestParam(name = "filterBy", required = false) String filterBy,
            @RequestParam(value = "q", required = false) final String search,
			Pageable pageable){
		String token = request.getHeader(tokenHeader);
		response = orderService.orderHistory(token, true, filterBy, search, pageable);

		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> getOrders(HttpServletRequest request,
		 	@RequestParam(name = "filterBy", required = false) String filterBy,
            @RequestParam(value = "q", required = false) final String search,
		 	Pageable pageable) {
		String token = request.getHeader(tokenHeader);
		response = orderService.orderHistory(token, false, filterBy, search, pageable);

		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@RequestMapping(value = "/{id}/details",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> getOrdersDetailsById(HttpServletRequest request,
			@PathVariable(name = "id") Long orderId) {
		String token = request.getHeader(tokenHeader);
		response = orderService.getOrderDetailsById(token, orderId);

		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{id}/cancel",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> cancelOrder(HttpServletRequest request,
			@PathVariable(name = "id") Long orderId) {
		String token = request.getHeader(tokenHeader);
		response = orderService.cancelOrder(token, orderId);

		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{id}/delivered",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> changeDeliveryStatus(HttpServletRequest request,
												   @PathVariable(name = "id") Long orderId) {
		String token = request.getHeader(tokenHeader);
		response = orderService.orderDelivered(orderId);

		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}
	

}
