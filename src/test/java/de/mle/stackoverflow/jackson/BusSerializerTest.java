package de.mle.stackoverflow.jackson;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BusSerializerTest {
	@Test
	public void serializeBus() throws JsonProcessingException {
		// given
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
		mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		Bus bus = new Bus();
		bus.setName("Sean");
		bus.setId(1);
		bus.setStudents(List.of(bus.new Student()));
		bus.setEmployer(bus.new Employer());

		// when
		String busAsString = mapper.writeValueAsString(bus);

		// then
		assertThat(busAsString).contains("employer");
	}
}
