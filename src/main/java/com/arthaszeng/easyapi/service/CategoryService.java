package com.arthaszeng.easyapi.service;

import com.arthaszeng.easyapi.entity.Category;
import com.arthaszeng.easyapi.entity.Product;
import com.arthaszeng.easyapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface CategoryService {
    Product findCategoryByProductId(String productId);

    Category findCategoryByCategoryId(String categoryId);
}
