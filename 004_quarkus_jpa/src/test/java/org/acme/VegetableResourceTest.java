package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
class VegetableResourceTest {
    @Test
    void testVegetableEndpoint() {
        given()
            .when()
                .get("/vegetables")
            .then()
                .statusCode(200)
                .body("[0].name", equalTo("Carrot"))
                .body("[0].nutritionalValue", equalTo(23));
    }
}