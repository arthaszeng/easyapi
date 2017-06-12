package com.arthaszeng.easyapi.integration.controller;

import com.arthaszeng.easyapi.controller.ProductController;
import com.arthaszeng.easyapi.entity.Category;
import com.arthaszeng.easyapi.entity.Product;
import com.arthaszeng.easyapi.entity.Source;
import com.arthaszeng.easyapi.integration.BaseIntegrationTest;
import com.arthaszeng.easyapi.repository.CategoryRepository;
import com.arthaszeng.easyapi.repository.ProductRepository;
import com.arthaszeng.easyapi.repository.SourceRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static java.lang.String.format;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerIntegrationTest extends BaseIntegrationTest {
    private static final String CAT_DESCRIPTION = "CAT_DESCRIPTION";
    private static final String CAT_DET_DESC = "CAT_DET_DESC";
    private static final String CODE = "CODE";
    private static final String CODE_DESC = "CODE_DESC";
    private static final String PROD_GROUP = "PROD_GROUP";

    @Autowired
    private ProductController productController;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SourceRepository sourceRepository;

    private MockMvc mockMvc;
    private Category category;
    private Source source;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void shouldQueryProductByProductId() throws Exception {
        category = new Category(CAT_DESCRIPTION, CAT_DET_DESC);
        source = new Source(CODE, CODE_DESC);

        categoryRepository.save(category);
        sourceRepository.save(source);

        Product product = new Product(PROD_GROUP, category, source);

        productRepository.save(product);

        mockMvc.perform(get(format("http://localhost:8081/products/%d", product.getId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(product.getId()))
                .andExpect(jsonPath("productGroup").value(product.getProductGroup()))
                .andExpect(jsonPath("category.categoryId").value(category.getCategoryId()))
                .andExpect(jsonPath("category.description").value(category.getDescription()))
                .andExpect(jsonPath("category.detailedDescription").value(category.getDetailedDescription()))
                .andExpect(jsonPath("source.sourceId").value(source.getSourceId()))
                .andExpect(jsonPath("source.code").value(source.getCode()))
                .andExpect(jsonPath("source.description").value(source.getDescription()));

    }

    @Test
    public void shouldAddAProductWithExistingCategoryAndSource() throws Exception {
        category = new Category(CAT_DESCRIPTION, CAT_DET_DESC);
        source = new Source(CODE, CODE_DESC);

        category = categoryRepository.save(category);
        source = sourceRepository.save(source);

        mockMvc.perform(post(
                format("http://localhost:8081/products/product?productGroup=%s&sourceId=%d&categoryId=%d",
                        PROD_GROUP,
                        source.getSourceId(),
                        category.getCategoryId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("productGroup").value(PROD_GROUP))
                .andExpect(jsonPath("category.categoryId").value(category.getCategoryId()))
                .andExpect(jsonPath("category.description").value(category.getDescription()))
                .andExpect(jsonPath("category.detailedDescription").value(category.getDetailedDescription()))
                .andExpect(jsonPath("source.sourceId").value(source.getSourceId()))
                .andExpect(jsonPath("source.code").value(source.getCode()))
                .andExpect(jsonPath("source.description").value(source.getDescription()));
    }
}