package com.arthaszeng.easyapi.service.category;

import com.arthaszeng.easyapi.entity.Category;

public interface CategoryService {

    Category findCategoryByCategoryId(Long categoryId);

    Category addCategory(Category category);
}
