package com.octopus.service.domain.repository;


import java.util.List;

import org.springframework.data.domain.Pageable;

import com.octopus.service.dto.OrderHistoryDTO;

public interface OrderRepositoryCustom {

    List<OrderHistoryDTO> getOrderHistory(Pageable pageable);

}
