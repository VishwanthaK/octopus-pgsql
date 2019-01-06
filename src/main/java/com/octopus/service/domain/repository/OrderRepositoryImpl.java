package com.octopus.service.domain.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;

import com.octopus.service.domain.model.Order;
import com.octopus.service.domain.model.OrderDetails;
import com.octopus.service.domain.model.QOrder;
import com.octopus.service.domain.model.QOrderDetails;
import com.octopus.service.dto.AddressDTO;
import com.octopus.service.dto.OrderHistoryDTO;
import com.octopus.service.dto.OrderItemDetailsDTO;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;

public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private static final QOrder qOrder = QOrder.order;
    private static final QOrderDetails qOrderDetails = QOrderDetails.orderDetails;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<OrderHistoryDTO> getOrderHistory(Long userId, String filterBy, Predicate predicate, Pageable pageable) {
        JPAQuery<?> jpaQuery = new JPAQuery<Void>(em);
        final Querydsl querydsl = new Querydsl(em, new PathBuilder<>(Order.class, "order"));
        BooleanExpression mainPredicate = null;

        if(Objects.nonNull(userId)) {
            mainPredicate = qOrder.user.id.eq(userId)
                    .and(predicate);
        } else {
            mainPredicate = qOrder.user.id.ne(0l)
                    .and(predicate);
        }

        if (StringUtils.isNotBlank(filterBy)) {
            BooleanExpression filterPredicate = qOrder.orderDetails.any().item.itemName.containsIgnoreCase(filterBy)
                    .or(qOrder.orderDetails.any().item.itemType.typeName.containsIgnoreCase(filterBy)
                    .or(qOrder.orderNumber.containsIgnoreCase(filterBy)
                    .or(qOrder.cancelled.eq(Boolean.valueOf(filterBy)))));
            mainPredicate = filterPredicate.and(mainPredicate);
        }

        List<OrderHistoryDTO> orderHistory = querydsl.applyPagination(pageable, jpaQuery.from(qOrder)
            .innerJoin(qOrder).on(qOrder.id.eq(qOrderDetails.order.id).and(mainPredicate))
            .select(Projections.bean(OrderHistoryDTO.class,
                qOrder.id.as("orderId"), qOrder.orderNumber, qOrder.itemTotal, qOrder.gstTotal, qOrder.grandTotal,
                qOrder.delivered, qOrder.cancelled,
                Projections.bean(AddressDTO.class, qOrder.userAddress.userObj.id.as("userId"), qOrder.userAddress.userObj.fullname.as("userName"),
                    qOrder.userAddress.userObj.email.as("userEmail"), qOrder.userAddress.userObj.mobileNumber.as("userPhoneNumber"),
                    qOrder.userAddress.id.as("userAddressId"), qOrder.userAddress.alternateContactNumber, qOrder.userAddress.houseOrFlatNum,
                    qOrder.userAddress.buildingOrHouseName, qOrder.userAddress.street, qOrder.userAddress.landmark, qOrder.userAddress.locality,
                    qOrder.userAddress.city, qOrder.userAddress.pincode).as("userInfo")
                ))).fetchResults().getResults();

        return orderHistory;
    }

    @Override
    public OrderHistoryDTO getOrderDetailsById(Long id) {
        JPAQuery<?> jpaQuery = new JPAQuery<Void>(em);

        Map<Long, OrderHistoryDTO> orderDetails =  jpaQuery
            .from(qOrder).leftJoin(qOrder.orderDetails, qOrderDetails)
            .select(Projections.bean(OrderHistoryDTO.class,
                qOrder.id.as("orderId"), qOrder.orderNumber, qOrder.itemTotal, qOrder.gstTotal, qOrder.grandTotal,
                qOrder.delivered, qOrder.cancelled,
                Projections.bean(AddressDTO.class, qOrder.userAddress.userObj.id.as("userId"), qOrder.userAddress.userObj.fullname.as("userName"),
                    qOrder.userAddress.userObj.email.as("userEmail"), qOrder.userAddress.userObj.mobileNumber.as("userPhoneNumber"),
                    qOrder.userAddress.id.as("userAddressId"), qOrder.userAddress.alternateContactNumber, qOrder.userAddress.houseOrFlatNum,
                    qOrder.userAddress.buildingOrHouseName, qOrder.userAddress.street, qOrder.userAddress.landmark, qOrder.userAddress.locality,
                    qOrder.userAddress.city, qOrder.userAddress.pincode).as("userInfo"),
                GroupBy.list(Projections.bean(OrderItemDetailsDTO.class, qOrderDetails.item.id.as("itemId"), qOrderDetails.item.itemName,
                    qOrderDetails.item.itemSize, qOrderDetails.qty, qOrderDetails.rate.as("value"), qOrderDetails.itemTotal.as("total"),
                    qOrderDetails.gst.id.as("gstId"), qOrderDetails.gst.gstValue, qOrderDetails.gstTotal)).as("itemDetails")))
            .where(qOrder.id.eq(id))
            .transform(GroupBy.groupBy(qOrder.id).as(
                Projections.bean(OrderHistoryDTO.class,
                    qOrder.id.as("orderId"), qOrder.orderNumber, qOrder.itemTotal, qOrder.gstTotal, qOrder.grandTotal,
                    qOrder.delivered, qOrder.cancelled,
                        Projections.bean(AddressDTO.class, qOrder.userAddress.userObj.id.as("userId"), qOrder.userAddress.userObj.fullname.as("userName"),
                            qOrder.userAddress.userObj.email.as("userEmail"), qOrder.userAddress.userObj.mobileNumber.as("userPhoneNumber"),
                            qOrder.userAddress.id.as("userAddressId"), qOrder.userAddress.alternateContactNumber, qOrder.userAddress.houseOrFlatNum,
                            qOrder.userAddress.buildingOrHouseName, qOrder.userAddress.street, qOrder.userAddress.landmark, qOrder.userAddress.locality,
                            qOrder.userAddress.city, qOrder.userAddress.pincode).as("userInfo"),
                        GroupBy.list(Projections.bean(OrderItemDetailsDTO.class, qOrderDetails.item.id.as("itemId"), qOrderDetails.item.itemName,
                            qOrderDetails.item.itemSize, qOrderDetails.qty, qOrderDetails.rate.as("value"), qOrderDetails.itemTotal.as("total"),
                            qOrderDetails.gst.id.as("gstId"), qOrderDetails.gst.gstValue, qOrderDetails.gstTotal)).as("itemDetails"))));

        return orderDetails.get(id);
    }
}
