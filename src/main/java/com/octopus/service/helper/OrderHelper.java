package com.octopus.service.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.octopus.service.domain.model.OrderEntity;
import com.octopus.service.domain.model.User;
import com.octopus.service.domain.model.UserAddress;
import com.octopus.service.dto.AddressDTO;
import com.octopus.service.dto.DeliveryDTO;
import com.octopus.service.dto.OrderDTO;
import com.octopus.service.dto.OrderItemDetailsDTO;
import com.octopus.service.dto.UserDTO;

@Component
public class OrderHelper {


    public List<DeliveryDTO> getDeliveryList(final List<OrderEntity> orderList) {
        final List<DeliveryDTO> deliveryList = new ArrayList<>();

        orderList.forEach(order -> {
            final DeliveryDTO deliveryDTO = new DeliveryDTO();

            //user Info
            final UserDTO userInfo = User.convertToUserDTO(order.getUser());
            deliveryDTO.setUserInfo(userInfo);
            //address Info
            final AddressDTO addressInfo = UserAddress.convertToAddressDTO(order.getUserAddress());
            deliveryDTO.setAddressInfo(addressInfo);
            //order Info
            final OrderDTO orderInfo = OrderEntity.convertToOrderDTO(order);
            deliveryDTO.setOrderInfo(orderInfo);
            //order Item Info
            final List<OrderItemDetailsDTO> orderItemInfo = OrderEntity
                    .convertToOrderItemDetailsDTOList(order.getOrderDetails());
            deliveryDTO.setOrderItemInfo(orderItemInfo);

            deliveryList.add(deliveryDTO);
        });

        return deliveryList;
    }

}
