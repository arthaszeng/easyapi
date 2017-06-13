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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SourceService sourceService;

    @RequestMapping("/{productId}")
    @ApiOperation(notes = "Get Product Details Via Querying Product ID", value = "Product ID", httpMethod = "GET")
    public ResponseEntity<Product> queryProduct(@PathVariable @ApiParam Long productId) {

        if (validateQueryParams(productId)) {
            Product product = productService.findCategoryByProductId(productId);
            return new ResponseEntity<>(product, OK);
        } else {
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "product", notes = "Add Product", httpMethod = "POST")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        if (validateAddParams(product)) {
            Product insertedProduct = productService.addProduct(product);
            return new ResponseEntity<>(insertedProduct, OK);
        } else {
            return null;
        }
    }

    private boolean validateAddParams(Product product) {
        return product.getProductGroup() != null
                && product.getCategory() != null
                && product.getSource() != null
                && !product.getProductGroup().isEmpty();
    }

    private boolean validateQueryParams(Long productId) {
        return productId > 0;
    }
}
