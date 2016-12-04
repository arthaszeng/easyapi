package com.arthaszeng.easyapi.service.implement;

import com.arthaszeng.easyapi.entity.Category;
import com.arthaszeng.easyapi.entity.Product;
import com.arthaszeng.easyapi.repository.CategoryRepository;
import com.arthaszeng.easyapi.repository.ProductRepository;
import com.arthaszeng.easyapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Product findCategoryByProductId(String productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Category findCategoryByCategoryId(String categoryId) {
        return categoryRepository.findByCategoryId(categoryId);
    }
}
