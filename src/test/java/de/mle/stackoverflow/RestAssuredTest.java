package de.mle.stackoverflow;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.junit.Ignore;
import org.junit.Test;

public class RestAssuredTest {
    @Test
    @Ignore
    public void getSpreadSheet() {
        RestAssured.baseURI =
                "https://st8.tuiprjuat.co.uk/searchpanel/departureairport/th" +
                        "?duration=7114&multiSelect=&when=17-04-2019&flexible=true" +
                        "&flexibleDays=3&preventCache=1555326084549";

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(403);
    }
}
