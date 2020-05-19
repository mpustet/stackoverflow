package de.mle.stackoverflow;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParserTest {
	@Test
	public void points() throws Exception {
		String jsonString = FileUtils.readFileToString(new File(JsonParserTest.class.getResource("/points.json").getFile()), "UTF-8");
		List<String> points = new ArrayList<>();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode actualObj = mapper.readTree(jsonString);

		// routes[*].legs[*].steps[*].polyline.points
		Iterator<JsonNode> routes = actualObj.get("routes").elements();
		while (routes.hasNext()) {
			JsonNode route = routes.next();
			String routePoints = route.get("overview_polyline").get("points").asText();
			Iterator<JsonNode> legs = route.get("legs").elements();
			while (legs.hasNext()) {
				Iterator<JsonNode> steps = legs.next().get("steps").elements();
				while (steps.hasNext()) {
					points.add(steps.next().get("polyline").get("points").asText());
				}
			}
		}
		assertThat(points.size()).isGreaterThan(0);
	}
}
