package de.mle.stackoverflow;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.guava.GuavaModule;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LearningTest {
	@Test
	public void deserialize() throws JsonParseException, JsonMappingException, IOException {
		String inputJson = "{ \"key1\": { \"value1\": [\"listele1\", \"listele2\" ] }, \"key2\": { \"value2\": [\"listele1\", \"listele2\" ] } }";
		Map<String, Map<String, List<String>>> readJson = new ObjectMapper().readValue(inputJson,
				new TypeReference<Map<String, Map<String, List<String>>>>() {
				});
		log.info("mapOfMaps: {}", readJson);

		List<String> listOfFirstInnerMap = readJson.get("key1").get("value1");
		log.info("first: {}", listOfFirstInnerMap);
		List<String> listOfSecondInnerMap = readJson.get("key2").get("value2");
		log.info("second: {}", listOfSecondInnerMap);

		List<String> elements = readJson.values() // Collection<Map<String, List<String>>>
				.stream() // Stream<Map<String, List<String>>>
				.flatMap(value -> value.values().stream()) // Stream<List<String>>
				.flatMap(listOfStrings -> listOfStrings.stream()) // Stream<String>
				.collect(Collectors.toList()); // List<String>
		log.info("elements: {}", elements);
	}

	@Test
	public void jacksonCsv() throws Exception {
		CsvSchema schema = CsvSchema.builder()
				.addColumn("obj")
				.addColumn("intValue")
				.addColumn("strValue")
				.build()
				.withHeader();

		ToSerialize toSerialize = new ToSerialize(Arrays.asList(new NestedObject(), new NestedObject()));

		String csv = new CsvMapper()
				.writerFor(ToSerialize.class)
				.with(schema)
				.writeValueAsString(toSerialize);

		log.info("\n{}", csv);
	}

	@Test
	public void jsonIgnoreSerialization() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();

		String serialized = objectMapper.writeValueAsString(new Credentials("user", "pass"));
		assertThat(serialized).isEqualTo("{\"username\":\"user\"}");

		Credentials credentials = objectMapper.readValue("{\"username\":\"user\",\"password\":\"pass\"}",
				Credentials.class);
		assertThat(credentials.getUsername()).isEqualTo("user");
		assertThat(credentials.getPassword()).isEqualTo("pass");
	}

	@Test
	public void guava() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new GuavaModule());

		GuavaBean optLong = objectMapper.readValue("{\"abc\":9}", GuavaBean.class);
		assertThat(optLong.getAbc().get()).isEqualTo(9);
	}
}
