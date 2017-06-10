package com.arthaszeng.easyapi.service.category;

import com.arthaszeng.easyapi.entity.Category;
import com.arthaszeng.easyapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findCategoryByCategoryId(Long categoryId) {
        return categoryRepository.findByCategoryId(categoryId);
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }
}
