package com.arthaszeng.easyapi.controller;

import com.arthaszeng.easyapi.entity.Category;
import com.arthaszeng.easyapi.entity.Product;
import com.arthaszeng.easyapi.entity.Source;
import com.arthaszeng.easyapi.service.category.CategoryService;
import com.arthaszeng.easyapi.service.product.ProductService;
import com.arthaszeng.easyapi.service.sourceService.SourceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SourceService sourceService;

    @RequestMapping("/product/{productId}")
    @ApiOperation(notes = "Get Product Details Via Querying Product ID", value = "Product ID", httpMethod = "GET")
    public Product queryProduct(@PathVariable @ApiParam Long productId) {
        return productService.findCategoryByProductId(productId);
    }

    @RequestMapping("/product/add")
    @ApiOperation(value = "product", notes = "Add Product", httpMethod = "POST")
    public Product addCategory(
            @RequestParam(name = "productGroup") @ApiParam String productGroup,
            @RequestParam(name = "sourceId") @ApiParam Long sourceId,
            @RequestParam(name = "categoryId") @ApiParam Long categoryId) {

        Category category = categoryService.findCategoryByCategoryId(categoryId);
        Source source = sourceService.findSourceBySourceId(sourceId);
        Product product = new Product(productGroup, category, source);
        return productService.addProduct(product);
    }
}
