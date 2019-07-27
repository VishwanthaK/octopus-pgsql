package com.octopus.service.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class OrderItemDetailsDTO implements Serializable {

    private static final long serialVersionUID = -9014818393821601284L;

    private Long itemId;
    private String itemName;
    private String itemSize;
    private String qtyType;
    private Double qty;
    private Double value;
    private Double total;

    private Long gstId;
    private Double gstValue;
    private Double gstTotal;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize;
    }

    public String getQtyType() {
        return qtyType;
    }

    public void setQtyType(String qtyType) {
        this.qtyType = qtyType;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Long getGstId() {
        return gstId;
    }

    public void setGstId(Long gstId) {
        this.gstId = gstId;
    }

    public Double getGstValue() {
        return gstValue;
    }

    public void setGstValue(Double gstValue) {
        this.gstValue = gstValue;
    }

    public Double getGstTotal() {
        return gstTotal;
    }

    public void setGstTotal(Double gstTotal) {
        this.gstTotal = gstTotal;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
    }
}
