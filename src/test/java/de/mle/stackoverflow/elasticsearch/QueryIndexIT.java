package de.mle.stackoverflow.elasticsearch;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

@SpringBootTest
public class QueryIndexIT {
    @Autowired
    private ProductRepository repo;
    @Autowired
    private ElasticsearchRestTemplate template;

    @Test
    public void queryRepo() {
        assertThat(template.indexOps(Product.class).exists()).isTrue();

        Product savedProduct = repo.save(new Product(null, "the name", "n/a"));
        Product foundProduct = repo.findByName("the name");

        assertThat(foundProduct).isEqualTo(savedProduct);
    }
}
