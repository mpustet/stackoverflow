package de.mle.stackoverflow.jackson;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TimeTest {
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void serializeInstant() throws JsonProcessingException {
        Time value = new Time(Instant.ofEpochMilli(1569238532033l));
        String time = mapper.writeValueAsString(value);
        assertThat(time).isEqualTo("{\"instant\":\"2019-09-23T11:35:32.033Z\"}");
    }
}
