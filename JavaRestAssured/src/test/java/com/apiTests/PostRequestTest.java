package com.apiTests;

import com.model.UserDTO;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.specification.Specifications.requestSpecification;

public class PostRequestTest {
    @Test
    @DisplayName("Тестирование тестового запроса Post с проверкой status code = 201")
    public void postRequestCheckStatusCode() {
        UserDTO udto = new UserDTO("morpheus", "leader");
        RestAssured.given()
                .spec(requestSpecification())
                .body(udto)
                .post("/api/users")
                .then()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    @DisplayName("Тестирование тестового запроса Post c проверкой key/value по полям name, job")
    public void postRequestCheckResponseJsonBody() {
        UserDTO udto = new UserDTO("morpheus", "leader");
        RestAssured.given()
                .spec(requestSpecification())
                .body(udto)
                .post("/api/users")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .assertThat()
                .body("name", Matchers.is("morpheus"))
                .body("job", Matchers.is("leader"));
    }
}
