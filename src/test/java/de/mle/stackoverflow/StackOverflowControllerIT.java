package de.mle.stackoverflow;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import de.mle.stackoverflow.jackson.Project;
import de.mle.stackoverflow.jackson.WorkPackageEstimateType;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StackOverflowControllerIT {
	@LocalServerPort
	private int port;

	@Test
	public void restAssured() {
		RestAssured.given()
				.baseUri("http://localhost:" + port)
				.accept(ContentType.JSON)
				.get("/jsonFile")
				.then()
				.statusCode(200)
				.body("al.findIndexOf { it.aid == 1461 }", is(2))
				.body("al.find { it.aid == 1461 }._c", is("Gurgaon1"))
				.body("al.find { it.aid == 1461 }.pc", is("122003"))
				.body("al", hasSize(4))
				.body("al[0].aid", is(1464));
		// .body("al", is(Arrays.asList("…")));
	}

	@Test
	public void restAssuredDeserialize() {
		Project project = RestAssured.given()
				.baseUri("http://localhost:" + port)
				.accept(ContentType.JSON)
				.get("/deserialize")
				.body().as(Project.class);
		
		assertThat(project.getEstimateType()).isEqualTo(WorkPackageEstimateType.WEEK);
		assertThat(project.getEstimateType().getDisplayName()).isEqualTo("Weeks");
	}
}
