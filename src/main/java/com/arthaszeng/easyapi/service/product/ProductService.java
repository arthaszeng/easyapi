package com.arthaszeng.easyapi.service.product;

import com.arthaszeng.easyapi.entity.Product;

public interface ProductService {

    Product findProductById(Long productId);

    Product addProduct(Product product);
}
