package GET;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PostmanEchoTest {

    private final String BASE_URL = "https://postman-echo.com/get";

    @Test
    void testStatusCode() {
        RestAssured
                .given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get(BASE_URL)
                .then()
                .statusCode(200);
    }

    @Test
    void testResponseBody() {
        Response response = RestAssured
                .given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get(BASE_URL);

        assertThat(response.jsonPath().getString("args.foo1"), equalTo("bar1"));
        assertThat(response.jsonPath().getString("args.foo2"), equalTo("bar2"));
        assertThat(response.statusCode(), is(200));
    }
}
