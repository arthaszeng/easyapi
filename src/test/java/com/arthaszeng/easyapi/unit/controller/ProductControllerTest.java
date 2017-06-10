package com.arthaszeng.easyapi.unit.controller;

import com.arthaszeng.easyapi.controller.ProductController;
import com.arthaszeng.easyapi.entity.Product;
import com.arthaszeng.easyapi.service.category.CategoryService;
import com.arthaszeng.easyapi.service.product.ProductService;
import com.arthaszeng.easyapi.service.sourceService.SourceService;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.util.ReflectionTestUtils.setField;

public class ProductControllerTest {
    private ProductController productController;
    private ProductService productService;
    private SourceService sourceService;
    private CategoryService categoryService;

    private long VALID_PRODUCT_ID = 1L;
    private long INVALID_PRODUCT_ID = -1L;
    private String PRODUCT_GROUP = "test";
    private Long SOURCE_ID = 1L;
    private Long CATEGORY_ID = 1L;
    private Long INVALID_ID = -1L;

    @Before
    public void setUp() throws Exception {
        productController = new ProductController();

        categoryService = mock(CategoryService.class);
        sourceService = mock(SourceService.class);
        productService = mock(ProductService.class);

        setField(productController, "productService", productService);
        setField(productController, "categoryService", categoryService);
        setField(productController, "sourceService", sourceService);
    }

    @Test
    public void shouldInvokeServiceToQueryProductWhenIdIsValid() throws Exception {
        productController.queryProduct(VALID_PRODUCT_ID);

        verify(productService, times(1)).findCategoryByProductId(1L);
    }

    @Test
    public void shouldNotInvokeServiceToQueryCategoryWhenIdIsInvalid() throws Exception {
        productController.queryProduct(INVALID_PRODUCT_ID);

        verify(productService, times(0)).findCategoryByProductId(any());
    }


    @Test
    public void shouldInvokeServiceToAddCategoryWhenParamsAreValid() throws Exception {
        productController.addCategory(PRODUCT_GROUP, SOURCE_ID, CATEGORY_ID);

        verify(categoryService, times(1)).findCategoryByCategoryId(CATEGORY_ID);
        verify(sourceService, times(1)).findSourceBySourceId(SOURCE_ID);
        verify(productService, times(1)).addProduct(any(Product.class));
    }

    @Test
    public void shouldNotInvokeServiceToAddCategoryWhenProductGroupIsInvalid() throws Exception {
        productController.addCategory(null, SOURCE_ID, CATEGORY_ID);

        verify(categoryService, times(0)).findCategoryByCategoryId(any());
        verify(sourceService, times(0)).findSourceBySourceId(any());
        verify(productService, times(0)).addProduct(any());
    }

    @Test
    public void shouldNotInvokeServiceToAddCategoryWhenSourceIdIsInvalid() throws Exception {
        productController.addCategory(PRODUCT_GROUP, INVALID_ID, CATEGORY_ID);

        verify(categoryService, times(0)).findCategoryByCategoryId(any());
        verify(sourceService, times(0)).findSourceBySourceId(any());
        verify(productService, times(0)).addProduct(any());
    }

    @Test
    public void shouldNotInvokeServiceToAddCategoryWhenCategoryIdIsInvalid() throws Exception {
        productController.addCategory(PRODUCT_GROUP, SOURCE_ID, INVALID_ID);

        verify(categoryService, times(0)).findCategoryByCategoryId(any());
        verify(sourceService, times(0)).findSourceBySourceId(any());
        verify(productService, times(0)).addProduct(any());
    }

}