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
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback()
public class CategoryServiceImplTest {
    @Autowired
    private CategoryService categoryService;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void shouldQueryCategoryById() throws Exception {
        Category category = categoryService.findCategoryByCategoryId(1L);

        assertThat(category.getCategoryId(), is(1L));
        assertThat(category.getDescription(), is("CATE_DESCRIPTION1"));
        assertThat(category.getDetailedDescription(), is("DET_DESCRIPTION_1"));
    }

    @Test
    public void shouldAddACategory() throws Exception {
        Category category = new Category("test", "test");

        categoryService.addCategory(category);

        Category insertedCategory = categoryService.findCategoryByCategoryId(category.getCategoryId());

        assertThat(insertedCategory.getCategoryId(), is(category.getCategoryId()));
        assertThat(insertedCategory.getDescription(), is(category.getDescription()));
        assertThat(insertedCategory.getDetailedDescription(), is(category.getDetailedDescription()));
    }

}