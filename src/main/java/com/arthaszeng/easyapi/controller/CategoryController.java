package com.arthaszeng.easyapi.controller;

import com.arthaszeng.easyapi.entity.Category;
import com.arthaszeng.easyapi.service.category.CategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/{categoryId}")
    @ApiOperation(notes = "Get Category Details Via Querying Category ID", value = "Category ID", httpMethod = "GET")
    public ResponseEntity<Category> queryCategory(@PathVariable @ApiParam Long categoryId) {

        if (validateQueryParams(categoryId)) {
            Category category = categoryService.findCategoryByCategoryId(categoryId);
            return new ResponseEntity<>(category, OK);
        } else {
            return null;
        }
    }


    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Category", notes = "Add Category Details", httpMethod = "POST")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        if (validateAddParams(category)) {
            Category insertedCategory = categoryService.addCategory(category);
            return new ResponseEntity<>(insertedCategory, OK);
        } else {
            return null;
        }
    }

    private boolean validateAddParams(Category category) {
        return category != null
                && category.getDescription() != null
                && category.getDetailedDescription() != null
                && !category.getDescription().isEmpty()
                && !category.getDetailedDescription().isEmpty();
    }

    private boolean validateQueryParams(Long categoryId) {
        return categoryId > 0L;
    }
}
