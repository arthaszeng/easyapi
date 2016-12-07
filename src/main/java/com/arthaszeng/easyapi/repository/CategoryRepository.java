package com.arthaszeng.easyapi.repository;

import com.arthaszeng.easyapi.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    Category findByCategoryId(Long categoryId);

    Category save(Category category);
}
