package com.octopus.service.service.impl;

import javax.transaction.Transactional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.octopus.service.domain.ApiResponse;
import com.octopus.service.domain.Error;
import com.octopus.service.domain.model.Order;
import com.octopus.service.domain.repository.OrderDetailsRepository;
import com.octopus.service.domain.repository.OrderRepository;
import com.octopus.service.dto.OrderData;
import com.octopus.service.dto.OrderHistoryDTO;
import com.octopus.service.exception.BadRequestexception;
import com.octopus.service.service.OrderService;
import com.octopus.service.util.AppHelper;
import com.octopus.service.util.AppMessages;
import com.octopus.service.validator.OrderValidator;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderValidator orderValidator;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderDetailsRepository orderDetailsRepository;
	@Autowired
	private AppHelper appHelper;


	@Override
	@Transactional
	public ApiResponse makeAnOrder(String token, OrderData ordersDetails) {
		List<Error> errors = orderValidator.validateOrder(ordersDetails.getOrders());

		if (!errors.isEmpty()) {
			throw new BadRequestexception(AppMessages.BAD_REQUEST, errors);
		}

		Order order = orderRepository.save(appHelper.createOrderEntity(token, ordersDetails));
		orderDetailsRepository.save(appHelper.createOrderDetailEntities(order, ordersDetails.getOrders()));

		return new ApiResponse(HttpStatus.OK.value(), AppMessages.SUCCESSFUL_ORDER);
	}





	@Override
	public ApiResponse orderHistory(String token, Pageable pageable) {
 		List<OrderHistoryDTO> orderHistory = orderRepository.getOrderHistory(pageable);

		return new ApiResponse(HttpStatus.OK.value(), orderHistory);
	}

	@Override
	public void cancelOrder() {
		
	}

}
