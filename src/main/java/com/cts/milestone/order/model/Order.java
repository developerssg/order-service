package com.cts.milestone.order.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel("Class representing a order tracked by the application.")
public class Order {

    @ApiModelProperty(notes = "This is Order UniqueId")
    private  String orderId;
    @ApiModelProperty(notes = "This is Product Id")
    private String productId;
    @ApiModelProperty(notes = "This is Product Name")
    private String productName;
    @ApiModelProperty(notes = "This is Order Name")
    private String orderName;
    @ApiModelProperty(notes = "This is Order Price")
    private BigDecimal orderPrice;

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
}
