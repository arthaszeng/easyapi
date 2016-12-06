package com.arthaszeng.easyapi.controller;

import com.arthaszeng.easyapi.entity.Category;
import com.arthaszeng.easyapi.entity.Product;
import com.arthaszeng.easyapi.entity.Source;
import com.arthaszeng.easyapi.service.category.CategoryService;
import com.arthaszeng.easyapi.service.product.ProductService;
import com.arthaszeng.easyapi.service.sourceService.SourceService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "Category API")
@RestController
public class CategoryController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    SourceService sourceService;

    @RequestMapping("/product/{productId}")
    @ApiOperation(
            notes = "Get Product Details Via Querying Product ID", value = "Product ID", httpMethod = "GET")
    @ApiImplicitParam(name = "header", value = "pass", required = false, dataType = "String")
    public Product queryProduct(@PathVariable @ApiParam String productId) {
        return productService.findCategoryByProductId(productId);
    }

    @RequestMapping("/category/{categoryId}")
    @ApiOperation(notes = "Get Category Details Via Querying Category ID", value = "Category ID", httpMethod = "GET")
    public List<Category> queryCategory(@PathVariable @ApiParam String categoryId) {
        return categoryService.findCategoryByCategoryId(categoryId);
    }

    @RequestMapping("/source/{sourceId}")
    @ApiOperation(notes = "Get source Details Via Querying Source ID", value = "Source ID", httpMethod = "GET")
    public Source querySource(@PathVariable @ApiParam String sourceId) {
        return sourceService.findSourceBySourceId(sourceId);
    }


}
