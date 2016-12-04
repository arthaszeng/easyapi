package com.arthaszeng.easyapi.repository;

import com.arthaszeng.easyapi.entity.Category;
import com.arthaszeng.easyapi.entity.Product;
import com.arthaszeng.easyapi.entity.Source;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.ManyToOne;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {

//    @Query(value = "SELECT p.id, p.product_group, p.category_id, p.source_id FROM Product p WHERE p.id = ?1")
    Product findProductById(String id);
}
