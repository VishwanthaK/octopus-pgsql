package com.octopus.service.dto;

import java.io.Serializable;
import java.util.List;

public class OrderData implements Serializable {

    private static final long serialVersionUID = 4382709798039916688L;

    private Long addressId;
    private List<OrderItemDTO> orders;
    private Double itemTotal;
    private Double gstTotal;
    private Double grandTotal;

    public Long getAddressId() {
        return addressId;
    }
    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public List<OrderItemDTO> getOrders() {
        return orders;
    }
    public void setOrders(List<OrderItemDTO> orders) {
        this.orders = orders;
    }

    public Double getItemTotal() { return itemTotal; }
    public void setItemTotal(Double itemTotal) { this.itemTotal = itemTotal; }

    public Double getGstTotal() { return gstTotal; }
    public void setGstTotal(Double gstTotal) { this.gstTotal = gstTotal; }

    public Double getGrandTotal() { return grandTotal; }
    public void setGrandTotal(Double grandTotal) { this.grandTotal = grandTotal; }
}
