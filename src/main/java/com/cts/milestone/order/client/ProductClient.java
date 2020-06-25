package com.cts.milestone.order.client;

import com.cts.milestone.order.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class ProductClient {

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<Product> getProduct(String productId){
        //Using Normal RestTemplate just pass Port number of product-service in url
       /* ResponseEntity<Product> productResp = restTemplate.exchange("http://localhost:8080/products/"+productId,
                HttpMethod.GET, null, new ParameterizedTypeReference<Product>() {
                });*/

        //Using Eureka server just pass url Name of Product-service
        ResponseEntity<Product> productResp = restTemplate.exchange("http://product-service/v1/products/"+productId,
                HttpMethod.GET, null, new ParameterizedTypeReference<Product>() {
                });
        return productResp;
    }
}
