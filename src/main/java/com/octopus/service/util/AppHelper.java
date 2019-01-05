package com.octopus.service.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.octopus.service.domain.model.Gst;
import com.octopus.service.domain.model.Item;
import com.octopus.service.domain.model.Order;
import com.octopus.service.domain.model.OrderDetails;
import com.octopus.service.domain.model.User;
import com.octopus.service.domain.model.UserAddress;
import com.octopus.service.dto.OrderData;
import com.octopus.service.dto.OrderItemDTO;
import com.octopus.service.service.UserService;

@Component
public class AppHelper {

    @Autowired
    private UserService userService;

    public String generateOrderNumber() {
        Long currentTimestamp = new DateTime().getMillis();

        return String.valueOf(Objects.hash(currentTimestamp));
    }

    public Order createOrderEntity(String token, OrderData ordersDetails) {
        Order order = new Order();
        UserAddress userAddress = new UserAddress();

        User user = userService.getUserByToken(token);
        userAddress.setId(ordersDetails.getAddressId());
        order.setUser(user);
        order.setUserAddress(userAddress);
        order.setOrderNumber(generateOrderNumber());
        order.setItemTotal(ordersDetails.getItemTotal());
        order.setGstTotal(ordersDetails.getGstTotal());
        order.setGrandTotal(ordersDetails.getGrandTotal());

        return order;
    }

    public List<OrderDetails> createOrderDetailEntities(Order order, List<OrderItemDTO> orderItemDTOS) {
        List<OrderDetails> multipleOrders = new ArrayList<>();

        orderItemDTOS.forEach(singleOrderDTO-> {
            OrderDetails singleOrder= new OrderDetails();
            Item item = new Item();
            Gst gst = new Gst();

            item.setId(singleOrderDTO.getItemId());
            gst.setId(singleOrderDTO.getGstId());
            singleOrder.setOrder(order);
            singleOrder.setItem(item);
            singleOrder.setGst(gst);
            singleOrder.setQty(singleOrderDTO.getQty());
            singleOrder.setRate(singleOrderDTO.getValue());
            singleOrder.setItemTotal(singleOrderDTO.getTotal());
            singleOrder.setGstTotal(singleOrderDTO.getGstTotal());

            multipleOrders.add(singleOrder);
        });

        return  multipleOrders;
    }
}
