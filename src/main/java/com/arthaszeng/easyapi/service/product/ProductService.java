package com.arthaszeng.easyapi.service.product;

import com.arthaszeng.easyapi.entity.Product;

public interface ProductService {

    Product findCategoryByProductId(Long productId);
}
