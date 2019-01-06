package com.octopus.service.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.octopus.service.domain.ApiResponse;
import com.octopus.service.dto.OrderData;
import com.querydsl.core.types.Predicate;

public interface OrderService {
	
	ApiResponse makeAnOrder(String token, OrderData orderDetails);

    ApiResponse orderHistory(String token, Boolean isUserRequest, String filterBy, Predicate predicate, Pageable pageable);

    ApiResponse getOrderDetailsById(String token, Long id);
	
	void cancelOrder();

}
