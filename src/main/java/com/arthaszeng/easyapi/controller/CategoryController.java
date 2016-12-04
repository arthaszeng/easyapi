package com.arthaszeng.easyapi.controller;

import com.arthaszeng.easyapi.repository.ProductRepository;
import com.arthaszeng.easyapi.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    @Autowired
    ProductRepository repository;

    @RequestMapping("/product/{productId}")
    public Product home(@PathVariable String productId) {
        Product product = repository.findById(productId);
        return product;
    }
}
