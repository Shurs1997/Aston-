package POST_FormData;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class POST_FormData {

    private final String BASE_URL = "https://postman-echo.com/post";

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
    void testResponseBodyFormPost() {
        Response response = RestAssured
                .given()
                .contentType(ContentType.URLENC)
                .formParam("foo1", "bar1")
                .formParam("foo2", "bar2")
                .when()
                .post(BASE_URL);

        assertThat(response.statusCode(), is(200));
        assertThat(response.jsonPath().getString("form.foo1"), equalTo("bar1"));
        assertThat(response.jsonPath().getString("form.foo2"), equalTo("bar2"));
    }
}
