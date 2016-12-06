package com.arthaszeng.easyapi.service.category;

import com.arthaszeng.easyapi.entity.Category;
import com.arthaszeng.easyapi.entity.Product;
import com.arthaszeng.easyapi.entity.Source;

import java.util.List;

public interface CategoryService {

    List<Category> findCategoryByCategoryId(String categoryId);

}
