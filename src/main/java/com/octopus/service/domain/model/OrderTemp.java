package com.octopus.service.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.octopus.service.domain.BaseModel;

@Entity
@Table(name = "order_history")
@JsonFilter("ORDER_FILTER_TEMP")
public class OrderTemp extends BaseModel {
	
	private static final long serialVersionUID = 7313266581456396338L;
	
	private User user;
	private UserAddress userAddress;
	private Item orderedItem;
	
	private String orderedQtyType;
	private Double orderedQtyValue;
	private LocalDateTime committedDeliveryTime;
	private String orderStatus;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne
	@JoinColumn(name="user_address_id")
	public UserAddress getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}
	
	@ManyToOne
	@JoinColumn(name="item_id")
	public Item getOrderedItem() {
		return orderedItem;
	}
	public void setOrderedItem(Item orderedItem) {
		this.orderedItem = orderedItem;
	}
	
	@Column(name = "ordered_qty_type")
	public String getOrderedQtyType() {
		return orderedQtyType;
	}
	public void setOrderedQtyType(String orderedQtyType) {
		this.orderedQtyType = orderedQtyType;
	}
	
	@Column(name = "ordered_qty_value")
	public Double getOrderedQtyValue() {
		return orderedQtyValue;
	}
	public void setOrderedQtyValue(Double orderedQtyValue) {
		this.orderedQtyValue = orderedQtyValue;
	}
	
	@Column(name = "committed_delivery_time")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	public LocalDateTime getCommittedDeliveryTime() {
		return committedDeliveryTime;
	}
	public void setCommittedDeliveryTime(LocalDateTime committedDeliveryTime) {
		this.committedDeliveryTime = committedDeliveryTime;
	}
	
	@Column(name = "order_status")
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
}
