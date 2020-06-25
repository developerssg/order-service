package com.cts.milestone.order.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="ORDERS")
public class OrderEntity {

    @Id
    @Column(name = "ORDER_ID")
    private  String orderId;
    @Column(name = "PRODUCT_ID")
    private  String productId;
    @Column(name = "ORDER_NAME")
    private String orderName;
    @Column(name = "ORDER_PRICE")
    private BigDecimal orderPrice;
    @Version
    @Column(name = "VERSION")
    private Integer version;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
