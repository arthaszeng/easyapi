package com.arthaszeng.easyapi.unit.controller;

import com.arthaszeng.easyapi.controller.CategoryController;
import com.arthaszeng.easyapi.entity.Category;
import com.arthaszeng.easyapi.service.category.CategoryService;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.ReflectionTestUtils.setField;

public class CategoryControllerTest {
    private static final long VALID_CATEGORY_ID = 1L;
    private static final long INVALID_CATEGORY_ID = -1L;
    private static final String DESCRIPTION = "TEST";
    private static final String DETAILED_DESCRIPTION = "TEST";

    private CategoryController categoryController;
    private CategoryService categoryService;

    @Before
    public void setUp() throws Exception {
        categoryController = new CategoryController();
        categoryService = mock(CategoryService.class);

        setField(categoryController, "categoryService", categoryService);
    }

    @Test
    public void shouldInvokeServiceToQueryCategoryWhenIdIsValid() throws Exception {
        categoryController.queryCategory(VALID_CATEGORY_ID);

        verify(categoryService, times(1)).findCategoryByCategoryId(VALID_CATEGORY_ID);
    }

    @Test
    public void shouldNotInvokeServiceToQueryCategoryWhenIdIsInvalid() throws Exception {
        categoryController.queryCategory(INVALID_CATEGORY_ID);

        verify(categoryService, times(0)).findCategoryByCategoryId(any());
    }


    @Test
    public void shouldInvokeServiceToAddCategoryWhenParamsAreValid() throws Exception {
        categoryController.addCategory(DESCRIPTION, DETAILED_DESCRIPTION);

        verify(categoryService, times(1)).addCategory(any(Category.class));
    }

    @Test
    public void shouldNotInvokeServiceToAddCategoryWhenParamsAreInvalid() throws Exception {
        categoryController.addCategory(null, null);

        verify(categoryService, times(0)).addCategory(any(Category.class));
    }
}