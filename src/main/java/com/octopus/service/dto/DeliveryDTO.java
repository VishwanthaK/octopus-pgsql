package com.octopus.service.dto;

import java.io.Serializable;
import java.util.List;

public class DeliveryDTO  implements Serializable {

    private UserDTO userInfo;
    private AddressDTO addressInfo;
    private OrderDTO orderInfo;
    private List<OrderItemDetailsDTO> orderItemInfo;

    public UserDTO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserDTO userInfo) {
        this.userInfo = userInfo;
    }

    public AddressDTO getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(AddressDTO addressInfo) {
        this.addressInfo = addressInfo;
    }

    public OrderDTO getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderDTO orderInfo) {
        this.orderInfo = orderInfo;
    }

    public List<OrderItemDetailsDTO> getOrderItemInfo() {
        return orderItemInfo;
    }

    public void setOrderItemInfo(List<OrderItemDetailsDTO> orderItemInfo) {
        this.orderItemInfo = orderItemInfo;
    }
}
