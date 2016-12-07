package com.arthaszeng.easyapi.repository;

import com.arthaszeng.easyapi.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    Product findProductById(Long id);

    Product save(Product product);
}

