package com.arthaszeng.easyapi.service.product;

import com.arthaszeng.easyapi.entity.Product;
import com.arthaszeng.easyapi.entity.Source;

public interface ProductService {

    Product findCategoryByProductId(String productId);
}
