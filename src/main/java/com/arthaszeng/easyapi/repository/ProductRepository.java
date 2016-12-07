package com.arthaszeng.easyapi.repository;

import com.arthaszeng.easyapi.entity.Category;
import com.arthaszeng.easyapi.entity.Product;
import com.arthaszeng.easyapi.entity.Source;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Transactional(readOnly = true)
    Product findProductById(Long id);

    @Transactional(rollbackFor = {Category.class, Source.class, Product.class})
    Product save(Product product);
}

