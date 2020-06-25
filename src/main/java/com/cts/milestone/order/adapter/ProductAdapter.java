package com.cts.milestone.order.adapter;


import com.cts.milestone.order.client.ProductClient;
import com.cts.milestone.order.exceptions.RecordNotFoundException;
import com.cts.milestone.order.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@Component
public class ProductAdapter {

    @Autowired
    private ProductClient productClient;

    public Product getProduct(String productId){
        Product product = null;
        try {
            ResponseEntity<Product> productResp = productClient.getProduct(productId);
            product = productResp.getBody();
        }catch(HttpClientErrorException e){
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)){
                throw new RecordNotFoundException("Product Not Found , productId="+productId);
            }
        }
        return product;
    }


}
