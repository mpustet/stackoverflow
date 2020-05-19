package de.mle.stackoverflow.jackson;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TimeIT {
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void serializeInstant() throws JsonProcessingException {
        Time value = new Time(Instant.ofEpochMilli(1569238532033l));
        String time = mapper.writeValueAsString(value);
        assertThat(time).isEqualTo("{\"instant\":\"2019-09-23T11:35:32.033Z\"}");
    }
}
