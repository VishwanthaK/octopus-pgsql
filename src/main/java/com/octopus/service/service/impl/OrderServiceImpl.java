package com.octopus.service.service.impl;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.octopus.service.domain.ApiResponse;
import com.octopus.service.domain.Error;
import com.octopus.service.domain.model.OrderEntity;
import com.octopus.service.domain.model.search.OrderPredicateSearchOperator;
import com.octopus.service.domain.repository.OrderDetailsRepository;
import com.octopus.service.domain.repository.OrderRepository;
import com.octopus.service.dto.OrderData;
import com.octopus.service.dto.OrderHistoryDTO;
import com.octopus.service.exception.BadRequestexception;
import com.octopus.service.service.OrderService;
import com.octopus.service.service.UserService;
import com.octopus.service.util.AppHelper;
import com.octopus.service.util.AppMessages;
import com.octopus.service.util.querygenerator.PredicateSearchQueryGenerator;
import com.octopus.service.util.querygenerator.SearchQueryVisitorFactory;
import com.octopus.service.validator.OrderValidator;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderValidator orderValidator;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderDetailsRepository orderDetailsRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private AppHelper appHelper;
	@Autowired
	private RSQLParser rsqlParser;
	@Autowired
	private SearchQueryVisitorFactory searchQueryVisitorFactory;

	@Override
	@Transactional
	public ApiResponse makeAnOrder(String token, OrderData ordersDetails) {
		List<Error> errors = orderValidator.validateOrder(ordersDetails.getOrders());

		if (!errors.isEmpty()) {
			throw new BadRequestexception(AppMessages.BAD_REQUEST, errors);
		}

        final OrderEntity order = appHelper.createOrderEntity(token, ordersDetails);

		if (Objects.isNull(order.getDeliveryScheduledOn())) {
		    LocalDateTime deliveryOn = LocalDateTime.now();
		    deliveryOn = deliveryOn.withTime(00, 00, 00, 00);
		    deliveryOn = deliveryOn.plusDays(1);
            order.setDeliveryScheduledOn(deliveryOn);
        }

		orderRepository.save(order);
		orderDetailsRepository.save(appHelper.createOrderDetailEntities(order, ordersDetails.getOrders()));

		return new ApiResponse(HttpStatus.OK.value(), AppMessages.SUCCESSFUL_ORDER);
	}





	@Override
	public ApiResponse orderHistory(String token, Boolean isUserRequest, String filterBy, String search, Pageable pageable) {
		Long userId = null;
		BooleanExpression searchPredicate = null;

		if (isUserRequest) {
			userId = userService.getUserIdByToken(token);
		}

		if(StringUtils.isNotBlank(search)) {
			final Node rootNode = rsqlParser.parse(search);
			final PredicateSearchQueryGenerator predicateSearchQueryGenerator =
					new PredicateSearchQueryGenerator(new OrderPredicateSearchOperator());
			searchPredicate = rootNode.accept(searchQueryVisitorFactory.create(predicateSearchQueryGenerator));
		}

 		List<OrderHistoryDTO> orderHistory = orderRepository
				.getOrderHistory(userId, filterBy, searchPredicate, pageable);

		return new ApiResponse(HttpStatus.OK.value(), orderHistory);
	}

	@Override
	public ApiResponse getOrderDetailsById(String token, Long id) {
	    OrderHistoryDTO orderDetails = orderRepository.getOrderDetailsById(id);

        return new ApiResponse(HttpStatus.OK.value(), orderDetails);
	}

	@Override
	public ApiResponse cancelOrder(String token, Long id) {
		Long userId = userService.getUserIdByToken(token);
		OrderEntity order = orderRepository.findOne(id);

		if (Objects.isNull(order) || !userId.equals(order.getUser().getId())) {
			List<Error> errors = new ArrayList<>();
			errors.add(new Error(String.valueOf(HttpStatus.NOT_FOUND.value()), "orderId", "Invalid id | order entity not found"));
			throw new BadRequestexception(AppMessages.BAD_REQUEST, errors);
		}

		order.setCancelled(Boolean.TRUE);
		orderRepository.save(order);

		return new ApiResponse(HttpStatus.OK.value(), AppMessages.CANCELLED_ORDER);
	}

	@Override
	public ApiResponse orderDelivered(Long id) {
		OrderEntity order = orderRepository.findOne(id);

		if (Objects.isNull(order)) {
			List<Error> errors = new ArrayList<>();
			errors.add(new Error(String.valueOf(HttpStatus.NOT_FOUND.value()), "orderId", "Invalid id | order entity not found"));
			throw new BadRequestexception(AppMessages.BAD_REQUEST, errors);
		}

		order.setDelivered(Boolean.TRUE);
		order.setDeliveredOn(new LocalDateTime());
		orderRepository.save(order);

		return new ApiResponse(HttpStatus.OK.value(), AppMessages.DELIVERED_ORDER);
	}

}
