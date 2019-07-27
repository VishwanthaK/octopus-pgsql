package com.octopus.service.service;

import org.springframework.data.domain.Pageable;

import com.octopus.service.domain.ApiResponse;

public interface DeliveryService {

    ApiResponse getDeliveryList(final String token, final String filterBy,
                                final String search, final Pageable pageable);
}
