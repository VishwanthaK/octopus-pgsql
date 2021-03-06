package com.octopus.service.domain.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.octopus.service.domain.BaseModel;
import com.octopus.service.dto.OrderDTO;
import com.octopus.service.dto.OrderItemDetailsDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

@Entity
@Table(name = "mstr_order")
@JsonFilter("ORDER_DETAILS_FILTER")
public class OrderEntity extends BaseModel {

    private static final long serialVersionUID = 7928042737931007567L;

    private User user;
    private UserAddress userAddress;

    private String orderNumber;
    private Double itemTotal;
    private Double gstTotal;
    private Double grandTotal;
    private LocalDateTime deliveryScheduledOn;
    private LocalDateTime deliveredOn;

    private Boolean isDelivered;
    private Boolean isModified;
    private Boolean isCancelled;

    private List<OrderDetails> orderDetails;

    @ManyToOne
    @JoinColumn(name="user_id")
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    @ManyToOne
    @JoinColumn(name="user_address_id")
    public UserAddress getUserAddress() { return userAddress; }
    public void setUserAddress(UserAddress userAddress) { this.userAddress = userAddress; }

    @Column(name = "order_number")
    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }

    @Column(name = "item_total")
    public Double getItemTotal() { return itemTotal; }
    public void setItemTotal(Double itemTotal) { this.itemTotal = itemTotal; }

    @Column(name = "gst_total")
    public Double getGstTotal() { return gstTotal; }
    public void setGstTotal(Double gstTotal) { this.gstTotal = gstTotal; }

    @Column(name = "grand_total")
    public Double getGrandTotal() { return grandTotal; }
    public void setGrandTotal(Double grandTotal) { this.grandTotal = grandTotal; }

    @Column(name = "delivery_scheduled_on")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    public LocalDateTime getDeliveryScheduledOn() { return deliveryScheduledOn; }
    public void setDeliveryScheduledOn(LocalDateTime deliveryScheduledOn) { this.deliveryScheduledOn = deliveryScheduledOn; }

    @Column(name = "delivery_on")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    public LocalDateTime getDeliveredOn() { return deliveredOn; }
    public void setDeliveredOn(LocalDateTime deliveredOn) { this.deliveredOn = deliveredOn; }

    @Column(name = "is_delivered")
    public Boolean getDelivered() { return isDelivered; }
    public void setDelivered(Boolean delivered) { isDelivered = delivered; }

    @Column(name = "is_modified")
    public Boolean getModified() { return isModified; }
    public void setModified(Boolean modified) { isModified = modified; }

    @Column(name = "is_cancelled")
    public Boolean getCancelled() { return isCancelled; }
    public void setCancelled(Boolean cancelled) { isCancelled = cancelled; }

    @OneToMany(mappedBy = "orderEntity", fetch = FetchType.LAZY)
    public List<OrderDetails> getOrderDetails() { return orderDetails; }
    public void setOrderDetails(List<OrderDetails> orderDetails) { this.orderDetails = orderDetails; }

    public static OrderDTO convertToOrderDTO(final OrderEntity order) {
        final OrderDTO orderDTO = new OrderDTO();

        orderDTO.setOrderId(order.getId());
        orderDTO.setOrderNumber(order.orderNumber);
        orderDTO.setItemTotal(order.itemTotal);
        orderDTO.setGstTotal(order.gstTotal);
        orderDTO.setGrandTotal(order.getGrandTotal());
        orderDTO.setDelivered(order.getDelivered());

        return orderDTO;
    }

    public static List<OrderItemDetailsDTO> convertToOrderItemDetailsDTOList(final List<OrderDetails> orderDetails) {
        final List<OrderItemDetailsDTO> orderItemDetailsDTOList = new ArrayList<>();

        orderDetails.forEach(orderDetail -> {
            final OrderItemDetailsDTO orderItemDetailDTO = new OrderItemDetailsDTO();

            orderItemDetailDTO.setItemId(orderDetail.getItem().getId());
            orderItemDetailDTO.setItemName(orderDetail.getItem().getItemName());
            orderItemDetailDTO.setItemSize(orderDetail.getItem().getItemSize());
            orderItemDetailDTO.setQty(orderDetail.getQty());
//            orderItemDetailDTO.setQtyType(orderDetail.get);
            orderItemDetailDTO.setValue(orderDetail.getRate());
            orderItemDetailDTO.setTotal(orderDetail.getItemTotal());
            orderItemDetailDTO.setGstId(orderDetail.getGst().getId());
            orderItemDetailDTO.setGstValue(orderDetail.getGst().getGstValue());
            orderItemDetailDTO.setGstTotal(orderDetail.getGstTotal());

            orderItemDetailsDTOList.add(orderItemDetailDTO);
        });

        return orderItemDetailsDTOList;
    }

    @PrePersist
    void onCreate() {
        if (Objects.isNull(isDelivered)) {
            isDelivered = Boolean.FALSE;
        }
        if (Objects.isNull(isModified)) {
            isModified = Boolean.FALSE;
        }
        if (Objects.isNull(isCancelled)) {
            isCancelled = Boolean.FALSE;
        }
    }
}
