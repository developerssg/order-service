package com.cts.milestone.order.service;

import com.cts.milestone.order.model.Order;
import com.cts.milestone.order.entity.OrderEntity;
import com.cts.milestone.order.model.OrderUpdate;
import com.cts.milestone.order.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.isNull;

@Component
public class OrderTranslator {
    public OrderEntity modelToEntity(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(getId());
        orderEntity.setProductId(order.getProductId());
        orderEntity.setOrderName(order.getOrderName());
        orderEntity.setOrderPrice(order.getOrderPrice());
        return orderEntity;
    }

    public Order entityToModel(OrderEntity orderEntity) {
        Order order = new Order();
        order.setOrderId(orderEntity.getOrderId());
        order.setProductId(orderEntity.getProductId());
        order.setOrderName(orderEntity.getOrderName());
        order.setOrderPrice(orderEntity.getOrderPrice());
        return order;
    }

    public Order entityToModelWithProduct(OrderEntity orderEntity,Product product) {
        Order order = entityToModel(orderEntity);
        order.setProductName(product.getProductName());
        return order;
    }

    public OrderEntity mergeEntity(OrderEntity orderEntity, OrderUpdate orderUpdate) {
        Objects.requireNonNull(orderUpdate,"Order Should be not null");
        if(!isNull(orderUpdate.getOrderName())){
            orderEntity.setOrderName(orderUpdate.getOrderName());
        }
        if(!isNull(orderUpdate.getOrderPrice())){
            orderEntity.setOrderPrice(orderUpdate.getOrderPrice());
        }
        return orderEntity;
    }

    public  String getId(){
        return UUID.randomUUID().toString().replace("-","").toUpperCase();
    }


}
