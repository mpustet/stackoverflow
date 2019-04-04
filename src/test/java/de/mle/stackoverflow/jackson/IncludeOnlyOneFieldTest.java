package de.mle.stackoverflow.jackson;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import lombok.AllArgsConstructor;
import lombok.Data;

public class IncludeOnlyOneFieldTest {
    @Test
    public void includeOnlyOneField() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("idOnlyFilter", SimpleBeanPropertyFilter.filterOutAllExcept("id"));

        Complex complex = new Complex("a string", List.of(), new Date());

        // when
        String complexAsString = mapper.writer(filters).writeValueAsString(complex);

        // then
        assertThat(complexAsString).isEqualTo("{\"id\":\"a string\"}");
    }

    @Data
    @JsonFilter("idOnlyFilter")
    @AllArgsConstructor
    class Complex {
        private String id;
        private List<String> aList;
        private Date aDate;
    }
}
