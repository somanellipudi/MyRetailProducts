package com.myretail.products.service;


import com.myretail.products.model.dto.ProductDetails;
import org.springframework.http.ResponseEntity;

public interface RetailService {

    public ResponseEntity<Object> productsService(String id);

    public ResponseEntity<Object> updateProductService(String id, ProductDetails productDetails);
}
