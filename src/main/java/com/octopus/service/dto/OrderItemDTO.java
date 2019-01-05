package com.octopus.service.dto;

import java.io.Serializable;

public class OrderItemDTO implements Serializable {

    private static final long serialVersionUID = 987439501390115317L;

    private Long itemId;
    private Long gstId;

    private Double qty;
    private Double value;
    private Double total;
    private Double gstTotal;

    public Long getItemId() { return itemId; }
    public void setItemId(Long itemId) { this.itemId = itemId; }

    public Long getGstId() { return gstId; }
    public void setGstId(Long gstId) { this.gstId = gstId; }

    public Double getQty() { return qty; }
    public void setQty(Double qty) { this.qty = qty; }

    public Double getValue() { return value; }
    public void setValue(Double value) { this.value = value; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public Double getGstTotal() { return gstTotal; }
    public void setGstTotal(Double gstTotal) { this.gstTotal = gstTotal; }
}

