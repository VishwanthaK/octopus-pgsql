package com.octopus.service.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.octopus.service.domain.BaseModel;

@Entity
@Table(name = "mstr_item")
@JsonFilter("ITEM_FILTER")
public class Item extends BaseModel {
	
	private static final long serialVersionUID = 4788682176826109275L;
	
	private Gst gstSlab;
	private ItemType itemType;
	
	private String itemName;
	private String itemSize;
	private Boolean isAvailable;
	private Double maxPrice;
	private Double actualPrice;
	private Double sellingPrice;
	private String qtyType;
	private Double qtyValue;
	
	@ManyToOne
	@JoinColumn(name="gst_id")
	public Gst getGstSlab() {
		return gstSlab;
	}
	public void setGstSlab(Gst gstSlab) {
		this.gstSlab = gstSlab;
	}
	
	@ManyToOne
	@JoinColumn(name="item_type_id")
	public ItemType getItemType() {
		return itemType;
	}
	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}
	
	@Column(name = "name")
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	@Column(name = "item_size")
	public String getItemSize() {
		return itemSize;
	}
	public void setItemSize(String itemSize) {
		this.itemSize = itemSize;
	}
	
	@Column(name = "is_available")
	public Boolean getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	@Column(name = "max_price")
	public Double getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}
	
	@Column(name = "actual_price")
	public Double getActualPrice() {
		return actualPrice;
	}
	public void setActualPrice(Double actualPrice) {
		this.actualPrice = actualPrice;
	}
	
	@Column(name = "selling_price")
	public Double getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	
	@Column(name = "qty_type")
	public String getQtyType() {
		return qtyType;
	}
	public void setQtyType(String qtyType) {
		this.qtyType = qtyType;
	}
	
	@Column(name = "qty_value")
	public Double getQtyValue() {
		return qtyValue;
	}
	public void setQtyValue(Double qtyValue) {
		this.qtyValue = qtyValue;
	}
}
