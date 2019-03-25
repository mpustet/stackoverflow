package de.mle.stackoverflow.elasticsearch;

import static org.assertj.core.api.Assertions.assertThat;

import org.awaitility.Awaitility;
import org.awaitility.Duration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class QueryIndexIT {
    @Autowired
    private ProductRepository repo;
    @Autowired
    private ElasticsearchTemplate template;

    @Test
    public void queryRepo() {
        assertThat(template.indexExists(Product.class)).isTrue();

        Product savedProduct = repo.save(new Product(null, "the name", "n/a"));
        Product foundProduct = repo.findByName("the name");

        assertThat(foundProduct).isEqualTo(savedProduct);
    }
}
