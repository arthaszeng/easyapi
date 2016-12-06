package com.arthaszeng.easyapi.service.product;

import com.arthaszeng.easyapi.entity.Product;
import com.arthaszeng.easyapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product findCategoryByProductId(Long productId) {
        return productRepository.findProductById(productId);
    }

    @Override
    public List<Product> addProduct(List<Product> products) {
        return productRepository.save(products);
    }
}
