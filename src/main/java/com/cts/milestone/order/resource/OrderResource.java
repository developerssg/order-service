package com.cts.milestone.order.resource;


import com.cts.milestone.order.entity.OrderEntity;
import com.cts.milestone.order.model.Order;
import com.cts.milestone.order.model.OrderUpdate;
import com.cts.milestone.order.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiOperation("Create,Get,Update,Delete Order - Version1")
@RestController
@RequestMapping("/v1/orders")
public class OrderResource {

    private static final Logger LOG = LoggerFactory.getLogger(Order.class);

    @Autowired
    private OrderService orderService;

    @ApiOperation("Creates a Order from the system. ")
    @PostMapping("/order")
    public ResponseEntity<String> createOrder(@ApiParam(value = "It is for creating Order")
                                                  @RequestBody Order order){
        String orderId = orderService.createOrder(order);
        LOG.info("Order created successfully, orderId={}",orderId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderId);
    }

    @ApiOperation("Fetches a Order from the system. ")
    @Cacheable(value = "orders", key = "#orderId")
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@ApiParam(value = "Id for the Order to Retrieve")
                                              @PathVariable String orderId){
        Order order = orderService.getOrder(orderId);
        LOG.info("Order fetched successfully, orderId={}",orderId);
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(order);
    }

    @ApiOperation("Fetches a AllOrder from the system. ")
    @GetMapping("/all")
    public ResponseEntity<List<Order>>getAllOrder(){
        List<Order> orders= orderService.getAllOrders();
        LOG.info("Order fetched successfully, orders={}",orders.size());
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(orders);
    }
    @GetMapping("/all/{productId}")
    public ResponseEntity<List<Order>> getAllOrderForProduct(@ApiParam(value = "Id for the retrieve product using OrderId")
                                                                 @PathVariable String productId){
        List<Order> orders= orderService.getAllOrdersForProduct(productId);
        LOG.info("Order fetched successfully, orders={},productId={}",orders.size(),productId);
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(orders);
    }


    @ApiOperation("Updates a Order from the system. 404 if the Order's identifier is not found.")
    @PutMapping("/{orderId}")
    public ResponseEntity<String> updateOrder(@ApiParam(value = "Id for the Order to Update")
                                                  @PathVariable String orderId, @RequestBody OrderUpdate orderUpdate){
        String eTag = orderService.updateOrder(orderId, orderUpdate);
        LOG.info("Order Updated successfully, orderId={}",orderId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .eTag(eTag)
                .build();
    }

    @ApiOperation("Deletes a Order from the system. 404 if the Order's identifier is not found.")
    @DeleteMapping("/{orderId}")
    public  ResponseEntity<Void> deleteOrder(@ApiParam(value = "Id for the Order to Delete")
                                                 @PathVariable String orderId){
        orderService.deleteOrder(orderId);
        LOG.info("Employee deleted successfully, orderId={}",orderId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
