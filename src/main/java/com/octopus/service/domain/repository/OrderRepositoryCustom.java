package com.octopus.service.domain.repository;


import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;

import com.octopus.service.domain.model.OrderEntity;
import com.octopus.service.dto.OrderHistoryDTO;
import com.querydsl.core.types.Predicate;

public interface OrderRepositoryCustom {

    List<OrderHistoryDTO> getOrderHistory(Long userId, String filterBy, Predicate predicate, Pageable pageable);

    OrderHistoryDTO getOrderDetailsById(Long id);

    List<OrderEntity> getOrderListByLocalityList(Set<String> localitySet, String filterBy, Predicate predicate, Pageable pageable);

}
