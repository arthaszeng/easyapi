package com.arthaszeng.easyapi.controller;

import com.arthaszeng.easyapi.entity.Category;
import com.arthaszeng.easyapi.entity.Product;
import com.arthaszeng.easyapi.entity.Source;
import com.arthaszeng.easyapi.exception.DatabaseException;
import com.arthaszeng.easyapi.service.category.CategoryService;
import com.arthaszeng.easyapi.service.product.ProductService;
import com.arthaszeng.easyapi.service.sourceService.SourceService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/")
@RestController
public class CategoryController {
    @Autowired
    private
    ProductService productService;

    @Autowired
    private
    CategoryService categoryService;

    @Autowired
    private SourceService sourceService;

    @RequestMapping("/product/{productId}")
    @ApiOperation(
            notes = "Get Product Details Via Querying Product ID", value = "Product ID", httpMethod = "GET")
    @ApiImplicitParam(name = "header", value = "pass", required = false, dataType = "String")
    public Product queryProduct(@PathVariable Long productId) {
        return productService.findCategoryByProductId(productId);
    }

    @RequestMapping("/category/{categoryId}")
    @ApiOperation(notes = "Get Category Details Via Querying Category ID", value = "Category ID", httpMethod = "GET")
    public Category queryCategory(@PathVariable Long categoryId) {
        return categoryService.findCategoryByCategoryId(categoryId);
    }

    @RequestMapping("/source/{sourceId}")
    @ApiOperation(notes = "Get source Details Via Querying Source ID", value = "Source ID", httpMethod = "GET")
    public Source querySource(@PathVariable Long sourceId) {
        return sourceService.findSourceBySourceId(sourceId);
    }

    @RequestMapping("/category/add")
    @ApiOperation(value = "Category", notes = "Add Category Details", httpMethod = "POST")
    public Category addCategory(
            @RequestParam(name = "description") String description,
            @RequestParam String detailedDescription) {

        Category category = new Category(description, detailedDescription);
        return categoryService.addCategory(category);
    }

    @RequestMapping("/product/add")
    @ApiOperation(value = "product", notes = "Add Product", httpMethod = "POST")
    public Product addCategory(
            @RequestParam(name = "productGroup") String productGroup,
            @RequestParam(name = "sourceId") Long sourceId,
            @RequestParam(name = "categoryId") Long categoryId) {

        Category category = categoryService.findCategoryByCategoryId(categoryId);
        Source source = sourceService.findSourceBySourceId(sourceId);
        Product product = new Product(productGroup, category, source);
        return productService.addProduct(product);
    }
    @RequestMapping("/source/add")
    @ApiOperation(value = "Source", notes = "Add Source", httpMethod = "POST", protocols = "app")
    public Source addSource(
            @RequestParam(name = "code") String code,
            @RequestParam(name = "description") String description) throws DatabaseException {

        Source source = null;

        try {
            source = new Source(code, description);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sourceService.addSource(source);
    }
}
