package com.arthaszeng.easyapi.service.category;

import com.arthaszeng.easyapi.entity.Category;
import com.arthaszeng.easyapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category findCategoryByCategoryId(Long categoryId) {
        return categoryRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<Category> addCategory(List<Category> categoryList) {
        return categoryRepository.save(categoryList);
    }
}
