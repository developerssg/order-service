package com.cts.milestone.order.util;


import com.cts.milestone.order.entity.OrderEntity;
import com.cts.milestone.order.model.Order;
import com.cts.milestone.order.model.OrderUpdate;
import com.cts.milestone.order.model.Product;

import java.math.BigDecimal;

public final class TestDataUtil {

    private TestDataUtil(){
    }

    public static Order createOrder(){
        Order order=new Order();
        order.setProductId("PRODUCT774AAB4144A32B98111C06B354");
        order.setProductName("Product");
        order.setOrderPrice(BigDecimal.TEN);
        order.setOrderId("ORDERC774CCB4144A32B98111C06B354");
        order.setOrderName("Order");
        return  order;
    }

    public static OrderEntity createOrderEntity(){
        OrderEntity orderEntity= new OrderEntity();
        orderEntity.setOrderName("Order");
        orderEntity.setOrderPrice(BigDecimal.TEN);
        orderEntity.setOrderId("ORDERC774CCB4144A32B98111C06B354");
        orderEntity.setProductId("PRODUCT774AAB4144A32B98111C06B354");
        return orderEntity;
    }
    public static Product createProduct(){
        Product product=new Product();
        product = new Product();
        product.setProductId("PRODUCT774AAB4144A32B98111C06B354");
        product.setProductName("Product");
        product.setPrice(BigDecimal.TEN);
        return product;
    }

    public static OrderUpdate createOrderUpdate(){
        OrderUpdate orderUpdate=new OrderUpdate();
        orderUpdate.setOrderName("OrderUpdate");
        orderUpdate.setOrderPrice(BigDecimal.TEN);
        return orderUpdate;
    }

}
