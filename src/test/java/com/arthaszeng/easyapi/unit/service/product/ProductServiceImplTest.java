package com.arthaszeng.easyapi.unit.service.product;

import com.arthaszeng.easyapi.entity.Category;
import com.arthaszeng.easyapi.entity.Product;
import com.arthaszeng.easyapi.entity.Source;
import com.arthaszeng.easyapi.service.category.CategoryService;
import com.arthaszeng.easyapi.service.product.ProductService;
import com.arthaszeng.easyapi.service.sourceService.SourceService;
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

    @Test
    public void shouldQueryAProductById() throws Exception {
        Product product = productService.findCategoryByProductId(1L);

        assertThat(product.getId(), is(1L));
        assertThat(product.getCategory().equals(categoryService.findCategoryByCategoryId(1L)), is(true));
        assertThat(product.getSource().equals(sourceService.findSourceBySourceId(1L)), is(true));
        assertThat(product.getProductGroup(), is("BANKING"));

    }

    @Test
    public void shouldAddAProduct() throws Exception {
        Category category = new Category("test", "test");
        Source source = new Source("test", "test");
        Product product = new Product("test", category, source);

        categoryService.addCategory(category);
        sourceService.addSource(source);
        productService.addProduct(product);

        Product insertedProduct = productService.findCategoryByProductId(product.getId());

        assertThat(insertedProduct.getId(), is(product.getId()));
        assertThat(insertedProduct.getProductGroup(), is(product.getProductGroup()));
        assertThat(insertedProduct.getCategory().equals(category), is(true));
        assertThat(insertedProduct.getSource().equals(source), is(true));
    }
}