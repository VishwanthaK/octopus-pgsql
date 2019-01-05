package com.octopus.service.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.octopus.service.domain.ApiResponse;
import com.octopus.service.dto.OrderData;

public interface OrderService {
	
	ApiResponse makeAnOrder(String token, OrderData orderDetails);

    ApiResponse orderHistory(String token, Pageable pageable);
	
	void cancelOrder();

}
