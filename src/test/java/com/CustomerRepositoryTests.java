
package com;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.arthaszeng.easyapi.entity.Category;
import com.arthaszeng.easyapi.entity.Product;
import com.arthaszeng.easyapi.entity.Source;
import com.arthaszeng.easyapi.repository.ProductRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = Product.class)
public class CustomerRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository customers;

    @Ignore
    @Test
    public void testFindByLastName() {
        Product product = new Product();
        Category category = new Category();
        Source source = new Source();
        product.setId("1");
        product.setProductGroup("SOURCE_GROUP");
        product.setCategory(category);
        product.setSource(source);
        entityManager.persist(product);

        Product selectedProduct = customers.findProductById(product.getId());

        assertThat(selectedProduct).isEqualTo(product.getId());
    }
}