package com.arthaszeng.easyapi.repository;

import com.arthaszeng.easyapi.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {

    Product findById(String id);
}
