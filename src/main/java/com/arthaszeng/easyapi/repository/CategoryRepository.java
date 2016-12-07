package com.arthaszeng.easyapi.repository;

import com.arthaszeng.easyapi.entity.Category;
import com.arthaszeng.easyapi.exception.DatabaseException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Transactional(readOnly = true)
    Category findByCategoryId(Long categoryId);

    @Transactional(rollbackFor = {DatabaseException.class})
    Category save(Category category);
}
