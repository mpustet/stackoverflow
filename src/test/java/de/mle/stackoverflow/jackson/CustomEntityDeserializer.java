package de.mle.stackoverflow.jackson;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class CustomEntityDeserializer {
    @Test
    public void customDeserialization() throws IOException {
        // given
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Entity.class, new CustomDeserializer(Entity.class));
        mapper.registerModule(module);

        // when
        Entity entity = mapper.readValue("{\"name\":\"xyz\",\"price\":\"90.00\"}", Entity.class);

        // then
        assertThat(entity.getName()).isEqualTo("xyz-3");
        assertThat(entity.getPrice()).isEqualTo(90.9f);
    }
}
