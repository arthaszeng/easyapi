package com.arthaszeng.easyapi.controller;

import com.arthaszeng.easyapi.entity.Category;
import com.arthaszeng.easyapi.service.category.CategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/category/{categoryId}")
    @ApiOperation(notes = "Get Category Details Via Querying Category ID", value = "Category ID", httpMethod = "GET")
    public ResponseEntity<Category> queryCategory(@PathVariable @ApiParam Long categoryId) {

        if (validateQueryParams(categoryId)) {
            Category category = categoryService.findCategoryByCategoryId(categoryId);
            return new ResponseEntity<>(category, OK);
        } else {
            return null;
        }
    }

    @RequestMapping("/category/add")
    @ApiOperation(value = "Category", notes = "Add Category Details", httpMethod = "POST")
    public Category addCategory(@RequestParam(name = "description") @ApiParam String description,
                                @RequestParam @ApiParam("detailed description") String detailedDescription) {


        if (validateAddParams(description, detailedDescription)) {
            Category category = new Category(description, detailedDescription);
            return categoryService.addCategory(category);
        } else {
            return null;
        }
    }

    private boolean validateAddParams(String description, String detailedDescription) {
        return description != null && detailedDescription != null;
    }


    private boolean validateQueryParams(Long categoryId) {
        return categoryId > 0L;
    }


}
