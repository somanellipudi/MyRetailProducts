package com.myretail.products.service.dao;

import com.mongodb.client.result.UpdateResult;
import com.myretail.products.model.dto.ProductDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.Cacheable;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

@Service
public class ProductDataService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Cacheable(value = "products")
    public ProductDetails getProductDetails(String id) throws DataAccessException {
        Query query = query(where("id").is(id));
        return mongoTemplate.findOne(query(where("id").is(id)), ProductDetails.class, "products");
    }

    @CachePut(value = "products", key ="#id")
    public Boolean updateProductDetails(String id, ProductDetails productDetails){
        Query query = query(where("id").is(id));
        Update update = update("productsDetails", productDetails);
        UpdateResult writeResult = mongoTemplate.updateFirst(query, update, ProductDetails.class, "productsDetails");

        return writeResult.getMatchedCount() == 1;
    }

}
