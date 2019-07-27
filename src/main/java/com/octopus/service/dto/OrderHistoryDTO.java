package com.octopus.service.dto;

import java.io.Serializable;
import java.util.List;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.octopus.service.util.LocalDateTimeCustomSerializer;

public class OrderHistoryDTO implements Serializable {

    private static final long serialVersionUID = -3990510220256024636L;

    private Long orderId;
    private String orderNumber;
    private Double itemTotal;
    private Double gstTotal;
    private Double grandTotal;
    private LocalDateTime createdOn;
    private LocalDateTime deliveryScheduledOn;
    private LocalDateTime deliveredOn;
    private boolean delivered;
    private boolean cancelled;
    private boolean modified;

    private UserAddressDTO userInfo;
    private List<OrderItemDetailsDTO> itemDetails;

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }

    public Double getItemTotal() { return itemTotal; }
    public void setItemTotal(Double itemTotal) { this.itemTotal = itemTotal; }

    public Double getGstTotal() { return gstTotal; }
    public void setGstTotal(Double gstTotal) { this.gstTotal = gstTotal; }

    public Double getGrandTotal() { return grandTotal; }
    public void setGrandTotal(Double grandTotal) { this.grandTotal = grandTotal; }

    @JsonSerialize(using = LocalDateTimeCustomSerializer.class)
    public LocalDateTime getCreatedOn() { return createdOn; }
    public void setCreatedOn(LocalDateTime createdOn) { this.createdOn = createdOn; }

    @JsonSerialize(using = LocalDateTimeCustomSerializer.class)
    public LocalDateTime getDeliveryScheduledOn() { return deliveryScheduledOn; }
    public void setDeliveryScheduledOn(LocalDateTime deliveryScheduledOn) { this.deliveryScheduledOn = deliveryScheduledOn; }

    @JsonSerialize(using = LocalDateTimeCustomSerializer.class)
    public LocalDateTime getDeliveredOn() { return deliveredOn; }
    public void setDeliveredOn(LocalDateTime deliveredOn) { this.deliveredOn = deliveredOn; }

    public boolean isDelivered() { return delivered; }
    public void setDelivered(boolean delivered) { this.delivered = delivered; }

    public boolean isCancelled() { return cancelled; }
    public void setCancelled(boolean cancelled) { this.cancelled = cancelled; }

    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }

    public UserAddressDTO getUserInfo() { return userInfo; }
    public void setUserInfo(UserAddressDTO userInfo) { this.userInfo = userInfo; }

    public List<OrderItemDetailsDTO> getItemDetails() { return itemDetails; }
    public void setItemDetails(List<OrderItemDetailsDTO> itemDetails) { this.itemDetails = itemDetails; }
}
