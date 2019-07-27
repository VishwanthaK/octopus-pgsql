package com.octopus.service.domain.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.octopus.service.domain.BaseModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "order_details")
@JsonFilter("ORDER_FILTER")
public class OrderDetails extends BaseModel {

    private static final long serialVersionUID = 1887532824129564214L;

    private OrderEntity orderEntity;
    private Item item;
    private Gst gst;

    private Double qty;
    private Double rate;
    private Double itemTotal;
    private Double gstTotal;
    private Boolean isCancelled;

    @ManyToOne
    @JoinColumn(name="order_id")
    public OrderEntity getOrderEntity() { return orderEntity; }
    public void setOrderEntity(OrderEntity orderEntity) { this.orderEntity = orderEntity; }

    @ManyToOne
    @JoinColumn(name="item_id")
    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }

    @ManyToOne
    @JoinColumn(name="gst_id")
    public Gst getGst() { return gst; }
    public void setGst(Gst gst) { this.gst = gst; }

    @Column(name = "qty")
    public Double getQty() { return qty; }
    public void setQty(Double qty) { this.qty = qty; }

    @Column(name = "rate")
    public Double getRate() { return rate; }
    public void setRate(Double rate) { this.rate = rate; }

    @Column(name = "item_total")
    public Double getItemTotal() { return itemTotal; }
    public void setItemTotal(Double itemTotal) { this.itemTotal = itemTotal; }

    @Column(name = "gst_total")
    public Double getGstTotal() { return gstTotal; }
    public void setGstTotal(Double gstTotal) { this.gstTotal = gstTotal; }

    @Column(name = "is_cancelled")
    public Boolean getCancelled() { return isCancelled; }
    public void setCancelled(Boolean cancelled) { isCancelled = cancelled; }

    @PrePersist
    void onCreate() {
        if (Objects.isNull(isCancelled)) {
            isCancelled = Boolean.FALSE;
        }
    }
}
