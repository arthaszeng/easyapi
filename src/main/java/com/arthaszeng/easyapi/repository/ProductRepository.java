package com.arthaszeng.easyapi.repository;

import com.arthaszeng.easyapi.entity.Category;
import com.arthaszeng.easyapi.entity.Product;
import com.arthaszeng.easyapi.entity.Source;
import com.arthaszeng.easyapi.exception.DatabaseException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Transactional(readOnly = true)
    Product findProductById(Long id);

    @Transactional(rollbackFor = {DatabaseException.class})
    Product save(Product product);
}

