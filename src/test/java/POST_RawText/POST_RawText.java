package POST_RawText;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class POST_RawText {

    private final String BASE_URL = "https://postman-echo.com/post";

    @Test
    void testStatusCodePost() {
        RestAssured
                .given()
                .contentType(ContentType.TEXT)
                .body("This is expected to be sent back as part of response body.")
                .when()
                .post(BASE_URL)
                .then()
                .statusCode(200);
    }

    @Test
    void testResponseBodyPost() {
        String expectedData = "This is expected to be sent back as part of response body.";

        Response response = RestAssured
                .given()
                .contentType(ContentType.TEXT)
                .body(expectedData)
                .when()
                .post(BASE_URL);


        assertThat(response.statusCode(), is(200));
        assertThat(response.jsonPath().getString("data"), equalTo(expectedData));
    }
}