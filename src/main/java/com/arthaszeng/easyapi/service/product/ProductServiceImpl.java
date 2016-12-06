package com.arthaszeng.easyapi.service.product;

import com.arthaszeng.easyapi.entity.Product;
import com.arthaszeng.easyapi.entity.Source;
import com.arthaszeng.easyapi.repository.CategoryRepository;
import com.arthaszeng.easyapi.repository.ProductRepository;
import com.arthaszeng.easyapi.repository.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product findCategoryByProductId(String productId) {
        return productRepository.findProductById(productId);
    }
}
