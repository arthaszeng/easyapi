package com.arthaszeng.easyapi.service.category;

import com.arthaszeng.easyapi.entity.Category;
import com.arthaszeng.easyapi.entity.Product;
import com.arthaszeng.easyapi.entity.Source;
import com.arthaszeng.easyapi.repository.CategoryRepository;
import com.arthaszeng.easyapi.repository.ProductRepository;
import com.arthaszeng.easyapi.repository.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findCategoryByCategoryId(String categoryId) {
        return categoryRepository.findByCategoryId(categoryId);
    }
}
