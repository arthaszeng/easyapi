package com.arthaszeng.easyapi.repository;

import com.arthaszeng.easyapi.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, String> {

    Category findByCategoryId(String categoryId);
}
