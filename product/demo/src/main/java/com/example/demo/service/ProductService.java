package com.example.demo.service;

import com.example.demo.entity.Product;
import java.util.List;

public interface ProductService {

    Product createProduct(Product product) throws Exception;
    Product getProduct(String id);
    Product updateProduct(Product product);
    List<Product> getTopRated();
    List<Product> findBySubCategory(String subCategory);
    List<String> getAllSubCategory();




}
