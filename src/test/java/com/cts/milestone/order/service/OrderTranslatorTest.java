package com.cts.milestone.order.service;

import com.cts.milestone.order.entity.OrderEntity;
import com.cts.milestone.order.model.Order;
import com.cts.milestone.order.model.OrderUpdate;
import com.cts.milestone.order.model.Product;
import com.cts.milestone.order.util.TestDataUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class OrderTranslatorTest {

    @InjectMocks
    private OrderTranslator orderTranslator;

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

    @Test(expected = RuntimeException.class)
    public void testModelToEntityRuntime(){
        orderTranslator.modelToEntity(null);
    }

    @Test
    public void testModelToEntitySuccess(){
        OrderEntity orderEntityResult = orderTranslator.modelToEntity(order);
        assertNotNull(orderEntityResult);
        assertEquals(order.getOrderName(),orderEntityResult.getOrderName());
        assertEquals(order.getProductId(),orderEntityResult.getProductId());
        assertEquals(order.getOrderPrice(),orderEntityResult.getOrderPrice());
    }

    @Test(expected = RuntimeException.class)
    public void testEntityToModelRuntime(){
        orderTranslator.entityToModel(null);
    }

    @Test
    public void testEntityToModelSuccess(){
        Order orderResult = orderTranslator.entityToModel(orderEntity);
        assertNotNull(orderResult);
        assertEquals(orderEntity.getOrderName(),orderResult.getOrderName());
        assertEquals(orderEntity.getOrderId(),orderResult.getOrderId());
        assertEquals(orderEntity.getProductId(),orderResult.getProductId());
        assertEquals(orderEntity.getOrderPrice(),orderResult.getOrderPrice());
    }

    @Test(expected = RuntimeException.class)
    public void testEntityToModelWithProductRuntime(){
        orderTranslator.entityToModelWithProduct(orderEntity,null);
    }
    @Test
    public void testEntityToModelWithProductSuccess(){
       Order orderRes= orderTranslator.entityToModelWithProduct(orderEntity,product);
       assertNotNull(orderRes);
       assertEquals(product.getProductName(),orderRes.getProductName());
    }
    @Test(expected = RuntimeException.class)
    public void testMergeEntityRuntime(){
        orderTranslator.mergeEntity(orderEntity,null);
    }

    @Test(expected = RuntimeException.class)
    public void testMergeEntityRuntimeEntityCheck(){
        orderTranslator.mergeEntity(null,orderUpdate);
    }

    @Test
    public void testMergeEntitySuccess(){
       OrderEntity orderEntityResult= orderTranslator.mergeEntity(orderEntity,orderUpdate);
       assertNotNull(orderEntityResult);
       assertEquals(orderUpdate.getOrderName(),orderEntityResult.getOrderName());
       assertEquals(orderUpdate.getOrderPrice(),orderEntityResult.getOrderPrice());
    }
}
