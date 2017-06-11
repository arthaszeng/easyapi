package com.arthaszeng.easyapi.integration.controller;

import com.arthaszeng.easyapi.controller.CategoryController;
import com.arthaszeng.easyapi.entity.Category;
import com.arthaszeng.easyapi.integration.BaseIntegrationTest;
import com.arthaszeng.easyapi.repository.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static java.lang.String.format;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class CategoryControllerIntegrationTest extends BaseIntegrationTest {
    @Autowired
    private CategoryController categoryController;

    @Autowired
    private CategoryRepository categoryRepository;

    private String DESCRIPTION = "TEST";
    private String DETAILED_DESCRIPTION = "TEST";

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    public void shouldQueryCategoryByCategoryId() throws Exception {
        Category category = categoryRepository.save(new Category(DESCRIPTION, DETAILED_DESCRIPTION));

        mockMvc.perform(get(format("http://localhost:8081/category/%d", category.getCategoryId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("description").value(DESCRIPTION))
                .andExpect(jsonPath("detailedDescription").value(DETAILED_DESCRIPTION));
    }

    @Test
    public void shouldAddCategory() throws Exception {

        mockMvc.perform(post(format("http://localhost:8081/category/add?description=%s&detailedDescription=%s", DESCRIPTION, DETAILED_DESCRIPTION)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("description").value(DESCRIPTION))
                .andExpect(jsonPath("detailedDescription").value(DETAILED_DESCRIPTION));
    }
}