package com.arthaszeng.easyapi.service.category;

import com.arthaszeng.easyapi.entity.Category;

import java.util.List;

public interface CategoryService {

    Category findCategoryByCategoryId(Long categoryId);

    List<Category> addCategory(List<Category> categoryList);
}
