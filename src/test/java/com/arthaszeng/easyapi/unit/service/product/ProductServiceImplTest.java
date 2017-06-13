package com.arthaszeng.easyapi.unit.service.product;

import com.arthaszeng.easyapi.entity.Category;
import com.arthaszeng.easyapi.entity.Product;
import com.arthaszeng.easyapi.entity.Source;
import com.arthaszeng.easyapi.service.category.CategoryService;
import com.arthaszeng.easyapi.service.product.ProductService;
import com.arthaszeng.easyapi.service.sourceService.SourceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class ProductServiceImplTest {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SourceService sourceService;

    private Product product;

    @Before
    public void setUp() throws Exception {
        Category category = categoryService.addCategory(new Category("TEST", "TEST"));
        Source source = sourceService.addSource(new Source("TEST", "TEST"));
        product = new Product("TEST", category, source);

        productService.addProduct(product);
    }

    @Test
    public void shouldQueryAProductById() throws Exception {
        Product result = productService.findProductById(product.getId());

        assertThat(result.equals(product), is(true));
    }

    @Test
    public void shouldAddAProduct() throws Exception {
        Category category = new Category("TEST", "TEST");
        Source source = new Source("TEST", "TEST");
        Product product = new Product("TEST", category, source);

        categoryService.addCategory(category);
        sourceService.addSource(source);
        productService.addProduct(product);

        Product insertedProduct = productService.findProductById(product.getId());

        assertThat(insertedProduct.equals(product), is(true));
    }
}