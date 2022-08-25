package com.myretail.products.controller;

import com.myretail.products.model.dto.ProductDetails;
import com.myretail.products.service.RetailService;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;


@RestController
@RequestMapping("/retail")
public class Controller {

    @Autowired
    RetailService retailService;

    Logger logger = LoggerFactory.getLogger(Controller.class);

    @GET
    @Path("/products/{productId}")
    public ResponseEntity<Object> productDetails(@PathParam("productId") @NonNull String id){
       // id.orElseThrow();
        logger.info("Getting data for productId={}", id);
        return retailService.productsService(id);
    }

    @PUT
    @Path("/products/{productId}")
    public ResponseEntity<Object> productDetails(@PathParam("productId") String id, ProductDetails productDetails){
        // id.orElseThrow();
        logger.info("updating data for productId={}", id);
        return retailService.updateProductService(id, productDetails);
    }


}
