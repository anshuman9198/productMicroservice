package com.example.demo.service.Impl;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Product;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Service

public class ProductServiceClass implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MongoTemplate mongotemplate;

    @Override
    public Product createProduct(Product product) throws Exception {
      if (getProduct(product.getProductId()) == null) {
        return productRepository.save(product);

    }
        throw new Exception();
    }

    @Override
    public List<Product> getTopRated()
    {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC,"rating" ));

        List<Product> productresp = new ArrayList<>();

        List<Product> temp=mongotemplate.find(query.limit(40), Product.class);
        Map<String,Integer> map=new HashMap<>();
        for(Product product:temp)
        {
            if(map.containsKey(product.getName()))
            {
                continue;
            }
            else
            {
                map.put(product.getName(),1);
                productresp.add(product);
            }
        }
        return productresp;
    }
    @Override
    public Product getProduct(String id) {
    if (productRepository.findOne(id) != null) {
        System.out.printf("result returned");
           return productRepository.findOne(id);
}
       System.out.printf("null returned");
        return null;
    }

    @Override
    public List<String> getAllSubCategory()
    {

        mongotemplate.getCollection("COLLECTION");
        return mongotemplate.getCollection("COLLECTION").distinct("subCategory");

    }



    @Override
    public Product updateProduct(Product product) {
         return productRepository.save(product);
    }
    @Override
    public List<Product> findBySubCategory(String subCategory)
    {

        return productRepository.findBySubCategory(subCategory);

    }


}
