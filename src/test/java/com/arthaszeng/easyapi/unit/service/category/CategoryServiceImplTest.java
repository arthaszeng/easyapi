package com.arthaszeng.easyapi.unit.service.category;

import com.arthaszeng.easyapi.entity.Category;
import com.arthaszeng.easyapi.service.category.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback()
public class CategoryServiceImplTest {
    @Autowired
    private CategoryService categoryService;

    private Category category;

    @Before
    public void setUp() throws Exception {
        category = new Category("TEST", "TEST");
        categoryService.addCategory(category);
    }

    @Test
    public void shouldQueryCategoryById() throws Exception {
        Category result = categoryService.findCategoryByCategoryId(category.getCategoryId());

        assertThat(result.equals(category), is(true));
    }

    @Test
    public void shouldAddACategory() throws Exception {
        Category category = new Category("TEST", "TEST");
        categoryService.addCategory(category);

        Category insertedCategory = categoryService.findCategoryByCategoryId(category.getCategoryId());

        assertThat(insertedCategory.equals(category), is(true));
    }
}