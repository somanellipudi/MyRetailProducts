package com.myretail.products.service;

import com.myretail.products.config.RestTemplateConfig;
import com.myretail.products.controller.Controller;
import com.myretail.products.model.response.BaseResponse;
import com.myretail.products.model.dto.ProductDetails;
import com.myretail.products.service.dao.ProductDataService;
import com.myretail.products.util.exceptions.NoProductException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

import java.util.Objects;

@Service
public class RetailServiceImpl implements RetailService {


    @Autowired
    RestTemplateConfig restTemplate;

    @Value("products.sourceUrl")
    private StringBuilder productSourceUrl;

    @Autowired
    ProductDataService dataService;

    Logger logger = LoggerFactory.getLogger(RetailService.class);


    @Override
    public ResponseEntity<Object> productsService(String id) throws ResourceAccessException, HttpClientErrorException {
        ProductDetails productDetails = new ProductDetails();
        ResponseEntity<ProductDetails> response = restTemplate.getRestTemplate().getForEntity(productSourceUrl.append(id).toString(), ProductDetails.class);

        if(!response.getStatusCode().is2xxSuccessful()){
                logger.error("product not found for id : {}", id);
                throw new NoProductException();
        } else{
                logger.debug("product details successfully retrieved");
                productDetails = dataService.getProductDetails(id);
                productDetails.setProductName(Objects.requireNonNull(response.getBody()).getProductName());
                return new ResponseEntity<>(productDetails, HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<Object> updateProductService(String id, ProductDetails productDetails) {
        logger.info("updating the data for id : {}", id);
        Boolean updated = dataService.updateProductDetails(id, productDetails);
        if(updated)
            return new ResponseEntity<>("updated", HttpStatus.OK);
        return new ResponseEntity<>("update failed", HttpStatus.BAD_REQUEST);
    }
}
