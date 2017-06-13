package com.arthaszeng.easyapi.unit.controller;

import com.arthaszeng.easyapi.controller.ProductController;
import com.arthaszeng.easyapi.entity.Category;
import com.arthaszeng.easyapi.entity.Product;
import com.arthaszeng.easyapi.entity.Source;
import com.arthaszeng.easyapi.service.category.CategoryService;
import com.arthaszeng.easyapi.service.product.ProductService;
import com.arthaszeng.easyapi.service.sourceService.SourceService;
import org.junit.Before;
import org.junit.Test;

import static com.arthaszeng.easyapi.unit.utils.POJOGenerator.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.ReflectionTestUtils.setField;

public class ProductControllerTest {

    private ProductController productController;
    private ProductService productService;
    private SourceService sourceService;
    private CategoryService categoryService;
    private Product product;

    @Before
    public void setUp() throws Exception {
        productController = new ProductController();
        product = new Product(PRODUCT_GROUP, new Category("", ""), new Source("", ""));

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

        verify(productService, times(1)).findCategoryByProductId(VALID_PRODUCT_ID);
    }

    @Test
    public void shouldNotInvokeServiceToQueryCategoryWhenIdIsInvalid() throws Exception {
        productController.queryProduct(INVALID_PRODUCT_ID);

        verify(productService, times(0)).findCategoryByProductId(any());
    }

    @Test
    public void shouldInvokeServiceToAddProductWhenParamsAreValid() throws Exception {
        productController.addProduct(product);

        verify(productService, times(1)).addProduct(any(Product.class));
    }

    @Test
    public void shouldNotInvokeServiceToAddProductWhenProductGroupIsInvalid() throws Exception {
        productController.addProduct(invalidProductWithInvalidProductGroup());

        verify(productService, times(0)).addProduct(any());
    }

    @Test
    public void shouldNotInvokeServiceToAddProductWhenSourceIsInvalid() throws Exception {
        productController.addProduct(invalidProductWithInvalidSource());

        verify(productService, times(0)).addProduct(any());
    }

    @Test
    public void shouldNotInvokeServiceToAddProductWhenCategoryIsInvalid() throws Exception {
        productController.addProduct(invalidProductWithInvalidCategory());

        verify(categoryService, times(0)).findCategoryByCategoryId(any());
        verify(sourceService, times(0)).findSourceBySourceId(any());
        verify(productService, times(0)).addProduct(any());
    }


}