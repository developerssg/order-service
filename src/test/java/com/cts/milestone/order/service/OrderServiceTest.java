package com.cts.milestone.order.service;

import com.cts.milestone.order.adapter.ProductAdapter;
import com.cts.milestone.order.entity.OrderEntity;
import com.cts.milestone.order.exceptions.RecordNotFoundException;
import com.cts.milestone.order.model.Order;
import com.cts.milestone.order.model.OrderUpdate;
import com.cts.milestone.order.model.Product;
import com.cts.milestone.order.repository.OrderRepository;

import com.cts.milestone.order.util.TestDataUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderTranslator orderTranslator;
    @Mock
    private ProductAdapter productAdapter;
    @InjectMocks
    private OrderService orderService;

    private OrderEntity orderEntity;

    private Order order;

    private Product product;

    private OrderUpdate orderUpdate;


    @Before
    public void setUp(){
        order = TestDataUtil.createOrder();
        orderEntity=TestDataUtil.createOrderEntity();
        product=TestDataUtil.createProduct();
        orderUpdate=TestDataUtil.createOrderUpdate();
    }

    //Get
    @Test(expected = RuntimeException.class)
    public void getOrderRepoException() {
        when(orderRepository.findById(anyString())).thenThrow(new RuntimeException(""));
        orderService.getOrder("ORDERC774CCB4144A32B98111C06B354");
    }

    @Test(expected = RecordNotFoundException.class)
    public void getOrderNotFoundException() {
        Optional<OrderEntity> optionalOrderEntity = Optional.empty();
        when(orderRepository.findById(anyString())).thenReturn(optionalOrderEntity);
        orderService.getOrder("ORDERC774CCB4144A32B98111C06B354");
    }
    @Test(expected = RecordNotFoundException.class)
    public void getProductNotFoundException() {
        Optional<OrderEntity> optionalOrderEntity = Optional.of(orderEntity);
        when(orderRepository.findById(anyString())).thenReturn(optionalOrderEntity);
        when(productAdapter.getProduct(anyString())).thenReturn(null);

        orderService.getOrder("ORDERC774CCB4144A32B98111C06B354");
    }

    @Test
    public void getOrderSuccess(){
        when(orderRepository.findById(anyString())).thenReturn(Optional.of(orderEntity));
        when(productAdapter.getProduct(anyString())).thenReturn(product);
        when(orderTranslator.entityToModelWithProduct(any(),any())).thenReturn(order);
        Order order=orderService.getOrder("ORDERC774CCB4144A32B98111C06B354");
        assertTrue(order!=null);
    }

    //Create
    @Test(expected = RuntimeException.class)
    public void createOrderTranslatorException() {
        when(orderTranslator.modelToEntity(any())).thenThrow(new RuntimeException(""));
        orderService.createOrder(order);
    }

    @Test(expected = RuntimeException.class)
    public void createOrderRepoException() {
        when(orderTranslator.modelToEntity(any())).thenReturn(orderEntity);
        when(orderRepository.save(orderEntity)).thenThrow(new RuntimeException(""));
        orderService.createOrder(order);
    }

    @Test
    public void createOrderSuccess() {
        when(orderTranslator.modelToEntity(any())).thenReturn(orderEntity);
        when(orderRepository.save(any())).thenReturn(orderEntity);
        String orderId= orderService.createOrder(order);
        assertTrue(orderId!=null);
        assertTrue(orderId.equals(orderEntity.getOrderId()));
    }

    //Update
    @Test(expected = RuntimeException.class)
    public void updateOrderRepoException(){
        when(orderRepository.findById(anyString())).thenThrow(new RuntimeException());
        orderService.updateOrder("ORDERC774CCB4144A32B98111C06B354",orderUpdate);
    }

    @Test(expected = RecordNotFoundException.class)
    public void updateOrderRecordNotFoundException(){
        when(orderRepository.findById(anyString())).thenReturn(Optional.empty());
        orderService.updateOrder("ORDERC774CCB4144A32B98111C06B354",orderUpdate);
    }

    @Test(expected = RuntimeException.class)
    public void updateOrderTranslatorException(){
        when(orderRepository.findById(anyString())).thenReturn(Optional.of(orderEntity));
        when(orderTranslator.mergeEntity(any(),any())).thenThrow(new RuntimeException());
        orderService.updateOrder("ORDERC774CCB4144A32B98111C06B354",orderUpdate);
    }

    @Test
    public void updateOrderSuccess(){
        when(orderRepository.findById(anyString())).thenReturn(Optional.of(orderEntity));
        when(orderTranslator.mergeEntity(any(),any())).thenReturn(orderEntity);
        when(orderRepository.save(orderEntity)).thenReturn(orderEntity);
        String orderId= orderService.updateOrder("ORDERC774CCB4144A32B98111C06B354",orderUpdate);
        assertTrue(orderId!=null);
    }


    //Delete
    @Test(expected = RuntimeException.class)
    public void deleteOrderRepoException() {
        when(orderRepository.findById(anyString())).thenThrow(new RuntimeException(""));
        orderService.deleteOrder("ORDERC774CCB4144A32B98111C06B354");
    }

    @Test(expected = RecordNotFoundException.class)
    public void deleteOrderRecordNotFoundException() {
        when(orderRepository.findById(anyString())).thenReturn(Optional.empty());
        orderService.deleteOrder("ORDERC774CCB4144A32B98111C06B354");
    }

     @Test
    public void deleteFoundSuccess(){
        when(orderRepository.findById(anyString())).thenReturn(Optional.of(orderEntity));
        doNothing().when(orderRepository).deleteById(anyString());
        orderService.deleteOrder("ORDERC774CCB4144A32B98111C06B354");
        verify(orderRepository).findById(anyString());
        verify(orderRepository).deleteById(anyString());
    }
}
