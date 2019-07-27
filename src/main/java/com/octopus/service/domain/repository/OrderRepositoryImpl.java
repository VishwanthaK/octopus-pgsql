package com.octopus.service.domain.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDateTime;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;

import com.octopus.service.domain.model.OrderEntity;
import com.octopus.service.domain.model.QItem;
import com.octopus.service.domain.model.QOrderDetails;
import com.octopus.service.domain.model.QOrderEntity;
import com.octopus.service.domain.model.QUser;
import com.octopus.service.domain.model.QUserAddress;
import com.octopus.service.dto.OrderHistoryDTO;
import com.octopus.service.dto.OrderItemDetailsDTO;
import com.octopus.service.dto.UserAddressDTO;
import com.octopus.service.util.AppConstants;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;

public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private static final QOrderEntity qOrderEntity = QOrderEntity.orderEntity;
    private static final QOrderDetails qOrderDetails = QOrderDetails.orderDetails;
    private static final QItem qItem = QItem.item;
    private static final QUser qUser = QUser.user;
    private static final QUserAddress qUserAddress = QUserAddress.userAddress;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<OrderHistoryDTO> getOrderHistory(
            final Long userId,
            final String filterBy,
            final Predicate predicate,
            final Pageable pageable) {
        JPAQuery<?> jpaQuery = new JPAQuery<Void>(em);
        final Querydsl querydsl = new Querydsl(em, new PathBuilder<>(OrderEntity.class, "order"));
        BooleanExpression userPredicate = null;

        if(Objects.nonNull(userId)) {
            userPredicate = qOrderEntity.user.id.eq(userId)
                    .and(predicate);
        } else {
            userPredicate = qOrderEntity.user.id.ne(0l)
                    .and(predicate);
        }

        if (StringUtils.isNotBlank(filterBy)) {
            BooleanExpression filterPredicate = qOrderEntity.orderDetails.any().item.itemName.containsIgnoreCase(filterBy)
                    .or(qOrderEntity.orderDetails.any().item.itemType.typeName.containsIgnoreCase(filterBy)
                    .or(qOrderEntity.orderNumber.containsIgnoreCase(filterBy)));
            userPredicate = filterPredicate.and(userPredicate);
        }

        List<OrderHistoryDTO> orderHistory = querydsl.applyPagination(pageable, jpaQuery
                .from(qOrderEntity)
                .innerJoin(qOrderEntity)
                .on(qOrderEntity.id.eq(qOrderDetails.orderEntity.id))
                .where(userPredicate)
                .select(getOrderHistoryListBean())
                .orderBy(qOrderEntity.id.desc())).fetchResults().getResults();

        return orderHistory;
    }

    @Override
    public OrderHistoryDTO getOrderDetailsById(Long id) {
        JPAQuery<?> jpaQuery = new JPAQuery<Void>(em);

        final Map<Long, OrderHistoryDTO> orderDetails =  jpaQuery
            .from(qOrderEntity)
            .innerJoin(qOrderEntity.orderDetails, qOrderDetails)
            .select(getOrderHistoryBean())
            .where(qOrderEntity.id.eq(id))
            .transform(GroupBy.groupBy(qOrderEntity.id).as(getOrderHistoryBean()));

        return orderDetails.get(id);
    }

    @Override
    public List<OrderEntity> getOrderListByLocalityList(
            final Set<String> localitySet,
            final String filterBy, final Predicate predicate,
            final Pageable pageable) {
        final JPAQuery<?> jpaQuery = new JPAQuery<Void>(em);
        final Querydsl querydsl = new Querydsl(em, new PathBuilder<>(OrderEntity.class, "delivery"));
        final LocalDateTime startAt = LocalDateTime.now().withTime(0,0, 0,0);
        final LocalDateTime endAt = LocalDateTime.now().withTime(23,59, 59,999);


        final List<OrderEntity> orders = querydsl.applyPagination(pageable, jpaQuery
                .from(qOrderEntity)
                .innerJoin(qOrderEntity.userAddress, qUserAddress)
                .select(qOrderEntity)
                .where(qUserAddress.locality
                        .in(localitySet)
                        .and(qOrderEntity.deliveryScheduledOn.between(startAt, endAt))))
                .fetch();

        return orders;
    }

    private QBean<OrderHistoryDTO> getOrderHistoryListBean() {
        return Projections.bean(OrderHistoryDTO.class,
                qOrderEntity.id.as("orderId"), qOrderEntity.orderNumber, qOrderEntity.itemTotal, qOrderEntity.gstTotal, qOrderEntity.grandTotal,
                qOrderEntity.createdOn, qOrderEntity.deliveryScheduledOn, qOrderEntity.deliveredOn, qOrderEntity.delivered, qOrderEntity.cancelled,
                qOrderEntity.modified, Projections.bean(UserAddressDTO.class,
                        qOrderEntity.userAddress.userObj.id.as("userId"), qOrderEntity.userAddress.userObj.fullname.as("userName"),
                        qOrderEntity.userAddress.userObj.email.as("userEmail"), qOrderEntity.userAddress.userObj.mobileNumber.as("userPhoneNumber"),
                        qOrderEntity.userAddress.id.as("userAddressId"), qOrderEntity.userAddress.alternateContactNumber,
                        qOrderEntity.userAddress.houseOrFlatNum, qOrderEntity.userAddress.buildingOrHouseName, qOrderEntity.userAddress.street,
                        qOrderEntity.userAddress.landmark, qOrderEntity.userAddress.locality, qOrderEntity.userAddress.city,
                        qOrderEntity.userAddress.pincode).as("userInfo"));
    }

    private QBean<OrderHistoryDTO> getOrderHistoryBean() {
        return Projections.bean(OrderHistoryDTO.class,
                qOrderEntity.id.as("orderId"), qOrderEntity.orderNumber, qOrderEntity.itemTotal, qOrderEntity.gstTotal, qOrderEntity.grandTotal,
                qOrderEntity.delivered, qOrderEntity.cancelled,
                Projections.bean(UserAddressDTO.class, qOrderEntity.userAddress.userObj.id.as("userId"), qOrderEntity.userAddress.userObj.fullname.as("userName"),
                        qOrderEntity.userAddress.userObj.email.as("userEmail"), qOrderEntity.userAddress.userObj.mobileNumber.as("userPhoneNumber"),
                        qOrderEntity.userAddress.id.as("userAddressId"), qOrderEntity.userAddress.alternateContactNumber, qOrderEntity.userAddress.houseOrFlatNum,
                        qOrderEntity.userAddress.buildingOrHouseName, qOrderEntity.userAddress.street, qOrderEntity.userAddress.landmark, qOrderEntity.userAddress.locality,
                        qOrderEntity.userAddress.city, qOrderEntity.userAddress.pincode).as("userInfo"),
                GroupBy.list(Projections.bean(OrderItemDetailsDTO.class, qOrderDetails.item.id.as("itemId"), qOrderDetails.item.itemName,
                        qOrderDetails.item.itemSize, qOrderDetails.qty, qOrderDetails.rate.as("value"), qOrderDetails.itemTotal.as("total"),
                        qOrderDetails.gst.id.as("gstId"), qOrderDetails.gst.gstValue, qOrderDetails.gstTotal)).as("itemDetails"));
    }

    private QBean<OrderHistoryDTO> getDeliveryDetails() {
        return Projections.bean(OrderHistoryDTO.class,
                qOrderEntity.id.as("orderId"),
                qOrderEntity.orderNumber,
                qOrderEntity.itemTotal,
                qOrderEntity.gstTotal,
                qOrderEntity.grandTotal,
                qOrderEntity.delivered,
                qOrderEntity.cancelled,
                Projections.bean(UserAddressDTO.class,
                        qUserAddress.userObj.id.as("userId"),
                        qUserAddress.userObj.fullname.as("userName"),
                        qUserAddress.userObj.email.as("userEmail"),
                        qUserAddress.userObj.mobileNumber.as("userPhoneNumber"),
                        qUserAddress.id.as("userAddressId"),
                        qUserAddress.alternateContactNumber,
                        qUserAddress.houseOrFlatNum,
                        qUserAddress.buildingOrHouseName,
                        qUserAddress.street,
                        qUserAddress.landmark,
                        qUserAddress.locality,
                        qUserAddress.city,
                        qUserAddress.pincode).as("userInfo"),
                GroupBy.list(Projections.bean(OrderItemDetailsDTO.class,
                        qItem.id.as("itemId"),
                        qItem.itemName,
                        qItem.itemSize,
                        qOrderDetails.qty,
                        qOrderDetails.rate.as("value"),
                        qOrderDetails.itemTotal.as("total"),
                        qOrderDetails.gst.id.as("gstId"),
                        qOrderDetails.gst.gstValue,
                        qOrderDetails.gstTotal)).as("itemDetails"));
    }
}
