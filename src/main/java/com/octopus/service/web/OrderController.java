package com.octopus.service.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.octopus.service.domain.SuccessResponse;
import com.octopus.service.service.OrderService;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
	
	private SuccessResponse response = null;
	
	@Value("${token.header}")
    private String tokenHeader;
	
	@Autowired
	private OrderService orderService;
	
	

}
