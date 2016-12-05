package com.arthaszeng.easyapi.service.categoryservice;

import com.arthaszeng.easyapi.entity.Category;
import com.arthaszeng.easyapi.entity.Product;
import com.arthaszeng.easyapi.entity.Source;

public interface CategoryService {

    Product findCategoryByProductId(String productId);

    Category findCategoryByCategoryId(String categoryId);
    
    Source findSourceBySourceId(String sourceId);

}
