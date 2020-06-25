package com.cts.milestone.order.resource;

import com.cts.milestone.order.exceptions.PreConditionedFailed;
import com.cts.milestone.order.exceptions.RecordNotFoundException;
import com.cts.milestone.order.model.Order;
import com.cts.milestone.order.service.OrderService;
import com.cts.milestone.order.util.TestDataUtil;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class OrderResourceTest {

    @InjectMocks
    private OrderResource orderResource;

    @InjectMocks
    private OrderExceptionHandler orderExceptionHandler;

    @Mock
    private OrderService orderService;

    private MockMvc mockMvc;

    private Order order;

    private final String ORDER_CREATE = "{\n" +
            "  \"orderName\": \"1st Wow order\",\n" +
            "  \"orderPrice\": 100.00 ,\n" +
            "  \"productId\": \"PRODUCT774CCB4144A32B98111C06B354\"\n" +
            "}";

    private final  String ORDER_UPDATE= "{\n" +
            "  \"orderName\": \"1st Order\",\n" +
            "  \"orderPrice\": 600.00\n" +
            "}";
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(orderResource)
                .setControllerAdvice(orderExceptionHandler)
                .build();
        order = TestDataUtil.createOrder();
    }

    @Test
    public void testGetOrderRecordNotFound() throws Exception {
        when(orderService.getOrder(anyString())).thenThrow(new RecordNotFoundException(""));
        this.mockMvc.perform(get("/v1/orders/ORDERC774CCB4144A32B98111C060000"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetOrderSuccess() throws Exception {
        when(orderService.getOrder(anyString())).thenReturn(order);
        this.mockMvc.perform(get("/v1/orders/ORDERC774CCB4144A32B98111C06B354"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateOrderRecordNotFound() throws Exception {
        when(orderService.createOrder(any())).thenThrow(new RecordNotFoundException(""));
        this.mockMvc.perform(post("/v1/orders/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ORDER_CREATE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateOrderSuccess() throws Exception {
        when(orderService.createOrder(any())).thenReturn("ORDERC774CCB4144A32B98111C06B344");
        this.mockMvc.perform(post("/v1/orders/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ORDER_CREATE))
                .andExpect(status().isCreated());
    }

    @Ignore
    @Test
    public void testUpdateOrderNotModified() throws Exception {
        when(orderService.updateOrder(anyString(),any())).thenThrow(new PreConditionedFailed(""));
        this.mockMvc.perform(put("/v1/orders/ORDERC774CCB4144A32B98111C060000")
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(ORDER_UPDATE))
                .andExpect(status().isPreconditionFailed());
    }

    @Ignore
    @Test
    public void testUpdateOrderSuccess() throws Exception {
        when(orderService.updateOrder(anyString(),any())).thenReturn("ORDERC774CCB4144A32B98111C06B354");
        this.mockMvc.perform(put("/v1/orders/ORDERC774CCB4144A32B98111C06B354")
        .contentType(MediaType.APPLICATION_JSON)
        .contentType(ORDER_UPDATE))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteOrderRecordNotFound() throws Exception {
       doThrow(new RecordNotFoundException("")).when(orderService).deleteOrder(anyString());
        this.mockMvc.perform(delete("/v1/orders/ORDERC774CCB4144A32B98111C060000"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteOrderSuccess() throws Exception {
       doNothing().when(orderService).deleteOrder(anyString());
        this.mockMvc.perform(delete("/v1/orders/ORDERC774CCB4144A32B98111C06B354"))
                .andExpect(status().isNoContent());
    }
}
