package com.octopus.service.domain.repository;

import com.octopus.service.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>,
OrderRepositoryCustom {

}
