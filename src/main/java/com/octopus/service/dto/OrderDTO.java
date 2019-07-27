package com.octopus.service.dto;

import java.util.List;

public class OrderDTO {

    private Long orderId;
    private String orderNumber;
    private Double itemTotal;
    private Double gstTotal;
    private Double grandTotal;
    private Boolean delivered;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Double getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(Double itemTotal) {
        this.itemTotal = itemTotal;
    }

    public Double getGstTotal() {
        return gstTotal;
    }

    public void setGstTotal(Double gstTotal) {
        this.gstTotal = gstTotal;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Boolean getDelivered() {
        return delivered;
    }

    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }
}
