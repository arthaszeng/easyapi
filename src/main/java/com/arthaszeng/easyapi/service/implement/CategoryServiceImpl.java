package com.arthaszeng.easyapi.service.implement;

import com.arthaszeng.easyapi.entity.Product;
import com.arthaszeng.easyapi.repository.ProductRepository;
import com.arthaszeng.easyapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    ProductRepository repository;

    @Override
    public Product findCategoryByProductId(String productId) {
        return repository.findById(productId);
    }
}
