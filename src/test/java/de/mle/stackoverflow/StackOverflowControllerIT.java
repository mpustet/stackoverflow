package de.mle.stackoverflow;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import de.mle.stackoverflow.jackson.Project;
import de.mle.stackoverflow.jackson.WorkPackageEstimateType;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@ExtendWith({ RestDocumentationExtension.class, SpringExtension.class })
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StackOverflowControllerIT {
    @LocalServerPort
    private int port;
    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    public void initRestDocs(RestDocumentationContextProvider restDocumentation) {
        webTestClient = webTestClient.mutate()
                .filter(documentationConfiguration(restDocumentation)
                        .operationPreprocessors()
                        .withResponseDefaults(prettyPrint())
                        .withRequestDefaults(prettyPrint()))
                .build();
    }

    @Test
    public void documentRequestBodyArray() {
        webTestClient
                .post()
                .uri("/contract")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .syncBody(List.of(new MessageContract("one"), new MessageContract("two")))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(document("documentArray",
                        requestFields(
                                fieldWithPath("[]").description("a message array"),
                                fieldWithPath("[].message").description("a message"))));
    }

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
