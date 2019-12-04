package de.mle.stackoverflow.elasticsearch;

import org.elasticsearch.client.transport.TransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

@Lazy
@Configuration
public class ElasticsearchConfiguration {
    @Bean
    public ElasticsearchTemplate elasticsearchTemplate(TransportClient client) {
        return new ElasticsearchTemplate(client);
    }
}
