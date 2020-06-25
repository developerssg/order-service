package com.cts.milestone.order.model;

import io.swagger.annotations.ApiModel;

import java.math.BigDecimal;

@ApiModel("Class representing a product tracked by the application.")
public class Product {

    private String productId;
    private String productName;
    private BigDecimal price;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
