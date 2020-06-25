package com.cts.milestone.order.model;

import java.math.BigDecimal;

public class OrderUpdate {

    private String orderName;
    private BigDecimal orderPrice;


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
}
