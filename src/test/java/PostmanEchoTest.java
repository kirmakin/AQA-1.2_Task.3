import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class PostmanEchoTest {
    String s = "some data";

    @Test
    public void shouldReturnStandardObject() {
        given()
                .baseUri("https://postman-echo.com")
                .body(s) // отправляемые данные (заголовки и query можно выставлять аналогично)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data", equalTo("s"))
                .body("headers.content-length", equalTo("9"))
                .body("headers.user-agent", equalTo("Apache-HttpClient/4.5.3 (Java/11)"))
                .body(matchesJsonSchemaInClasspath("postmanEcho.schema.json"))
        ;
    }
}
