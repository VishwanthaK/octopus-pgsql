package com.octopus.service.domain.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;

import com.octopus.service.domain.model.OrderDetails;
import com.octopus.service.domain.model.QOrder;
import com.octopus.service.domain.model.QOrderDetails;
import com.octopus.service.dto.AddressDTO;
import com.octopus.service.dto.OrderHistoryDTO;
import com.octopus.service.dto.OrderItemDetailsDTO;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;

public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private static final QOrder qOrder = QOrder.order;
    private static final QOrderDetails qOrderDetails = QOrderDetails.orderDetails;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<OrderHistoryDTO> getOrderHistory(Pageable pageable) {
        JPAQuery<?> jpaQuery = new JPAQuery<Void>(em);
        final Querydsl querydsl = new Querydsl(em, new PathBuilder<>(OrderDetails.class, "orderDetails"));

        Map<Long, OrderHistoryDTO> transform = jpaQuery
                .from(qOrder).leftJoin(qOrder.orderDetails, qOrderDetails)
                .transform(GroupBy.groupBy(qOrder.id).as(
                        Projections.bean(OrderHistoryDTO.class,
                                qOrder.id.as("orderId"),
                                qOrder.orderNumber,
                                qOrder.itemTotal,
                                qOrder.gstTotal,
                                qOrder.grandTotal,
                                qOrder.delivered,
                                qOrder.cancelled,
                                Projections.bean(AddressDTO.class,
                                        qOrder.userAddress.userObj.id.as("userId"),
                                        qOrder.userAddress.userObj.fullname.as("userName"),
                                        qOrder.userAddress.userObj.email.as("userEmail"),
                                        qOrder.userAddress.userObj.mobileNumber.as("userPhoneNumber"),
                                        qOrder.userAddress.id.as("userAddressId"),
                                        qOrder.userAddress.alternateContactNumber,
                                        qOrder.userAddress.houseOrFlatNum,
                                        qOrder.userAddress.buildingOrHouseName,
                                        qOrder.userAddress.street,
                                        qOrder.userAddress.landmark,
                                        qOrder.userAddress.locality,
                                        qOrder.userAddress.city,
                                        qOrder.userAddress.pincode
                                ).as("userInfo"),
                                GroupBy.list(
                                        Projections.bean(OrderItemDetailsDTO.class,
                                                qOrderDetails.item.id.as("itemId"),
                                                qOrderDetails.item.itemName,
                                                qOrderDetails.item.itemSize,
                                                qOrderDetails.qty,
                                                qOrderDetails.rate.as("value"),
                                                qOrderDetails.itemTotal.as("total"),
                                                qOrderDetails.gst.id.as("gstId"),
                                                qOrderDetails.gst.gstValue,
                                                qOrderDetails.gstTotal)).as("itemDetails"))));

        final List<OrderHistoryDTO> orderHistory = new ArrayList<>(transform.values());

        return orderHistory;
    }
}
