package com.arthaszeng.easyapi.service.product;

import com.arthaszeng.easyapi.entity.Product;

import java.util.List;

public interface ProductService {

    Product findCategoryByProductId(Long productId);

    Product addProduct(Product product);
}
