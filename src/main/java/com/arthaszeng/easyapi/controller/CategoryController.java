package com.arthaszeng.easyapi.controller;

import com.arthaszeng.easyapi.entity.Category;
import com.arthaszeng.easyapi.entity.Product;
import com.arthaszeng.easyapi.entity.Source;
import com.arthaszeng.easyapi.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Category API")
@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping("/product/{productId}")
    @ApiOperation(notes="Get Product Details Via Querying Product ID",value="Product ID",httpMethod="GET")
    public Product queryProduct(@PathVariable @ApiParam String productId) {
        Product product = categoryService.findCategoryByProductId(productId);
        return product;
    }

    @RequestMapping("/category/{categoryId}")
    @ApiOperation(notes="Get Category Details Via Querying Category ID",value="Category ID",httpMethod="GET")
    public Category queryCategory(@PathVariable @ApiParam String categoryId) {
        Category category = categoryService.findCategoryByCategoryId(categoryId);
        return category;
    }
    
    @RequestMapping("/source/{sourceId}")
    @ApiOperation(notes="Get source Details Via Querying source ID",value="source ID",httpMethod="GET")
    public Source querysource(@PathVariable @ApiParam String sourceId) {
        Source source = categoryService.findSourceBySourceId(sourceId);
        return source;
    }
    
    
}
