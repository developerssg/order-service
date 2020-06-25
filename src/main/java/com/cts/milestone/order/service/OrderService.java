package com.cts.milestone.order.service;

import com.cts.milestone.order.adapter.ProductAdapter;
import com.cts.milestone.order.client.ProductClient;
import com.cts.milestone.order.exceptions.RecordNotFoundException;
import com.cts.milestone.order.model.Order;
import com.cts.milestone.order.model.Product;
import com.cts.milestone.order.repository.OrderRepository;
import com.cts.milestone.order.entity.OrderEntity;
import com.cts.milestone.order.model.OrderUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderTranslator orderTranslator;
    @Autowired
    private ProductAdapter productAdapter;

    public String createOrder(Order order) {
        OrderEntity orderEntity = orderTranslator.modelToEntity(order);
        orderRepository.save(orderEntity);
        return orderEntity.getOrderId();
    }

    public Order getOrder(String orderId) {
        Optional<OrderEntity> orderEntity= orderRepository.findById(orderId);
        if(!orderEntity.isPresent()){
            throw  new RecordNotFoundException("Order is not found ,orderId="+orderId);
        }
        return enrichOrder(orderEntity.get());
    }

    public String updateOrder(String orderId, OrderUpdate orderUpdate) {
        Optional<OrderEntity> orderEntity= orderRepository.findById(orderId);
        if(!orderEntity.isPresent()){
            throw  new RecordNotFoundException("Order is not found, orderId="+orderId);
        }
        OrderEntity mergerOrder = orderTranslator.mergeEntity(orderEntity.get(), orderUpdate);
        orderRepository.save(mergerOrder);
        return orderId;
    }


    public void deleteOrder(String orderId) {
        Optional<OrderEntity> orderEntity= orderRepository.findById(orderId);
        if(!orderEntity.isPresent()){
            throw  new RecordNotFoundException("Order is not found, orderId="+orderId);
        }
        orderRepository.deleteById(orderId);
    }

    public List<Order> getAllOrders() {
        List<OrderEntity> orderEntities =  orderRepository.findAll();
        List<Order> orders = orderEntities.stream()
                .map(this::enrichOrder)
                .collect(Collectors.toList());
        return orders;
    }

    public List<Order> getAllOrdersForProduct(String productId) {
        List<OrderEntity> orderEntities =  orderRepository.findByProductId(productId);
        List<Order> orders = orderEntities.stream()
                .map(this::enrichOrder)
                .collect(Collectors.toList());
        return orders;
    }

    private Order enrichOrder(OrderEntity orderEntity) {
        Product product = productAdapter.getProduct(orderEntity.getProductId());
        if(isNull(product)){
            throw new RecordNotFoundException("Product is not found ,productId="+ orderEntity.getProductId());
        }
        return orderTranslator.entityToModelWithProduct(orderEntity, product);
    }
}
