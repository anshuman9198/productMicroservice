package com.example.demo.controller;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getproduct")
    public Product getProduct(@RequestParam String id)
    {
        Product product = productService.getProduct(id);
        return  product;
    }

    @PostMapping("/product")
    public Product createProduct(@RequestBody Product product) throws  Exception
    {
        try {
            return productService.createProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
        }




        return null;
    }
    @GetMapping("/gettoprated")
    public List<Product> getTopRated()
    {
        return productService.getTopRated();
    }
    @GetMapping("/getsubcategory")
    public List<Product> getSubCategory(@RequestParam String subcategory)
    {
        return productService.findBySubCategory(subcategory);
    }
    @GetMapping("/getallsubcategories")
    public List<String> getAllSubCategory()
    {
        return productService.getAllSubCategory();
    }



    @PostMapping("/ratingupdate")
    public Product ratingUpdate(@RequestParam String id,@RequestParam int rating)
    {
        Product product = productService.getProduct(id);
        product.setRating(rating);
        try {

            Product response= productService.updateProduct(product);
           // productService.updateTopSuggestion(id,rating);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    @PostMapping("/availabilityupdate")
    public Product availabilityUpdate(@RequestParam String id,@RequestParam boolean availability)
    {
        Product product = productService.getProduct(id);
        product.setAvailability(availability);
        try {
            return productService.updateProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @PutMapping("/product")
    public Product updateProduct(@RequestBody Product product) {

        try {
            return productService.updateProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }





}
