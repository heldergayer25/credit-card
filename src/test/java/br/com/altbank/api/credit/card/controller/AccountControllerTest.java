package br.com.altbank.api.credit.card.controller;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@QuarkusTest
public class AccountControllerTest {

    @Inject
    AccountController accountController;

    @Test
    public void testHealth() {
        given()
                .contentType(ContentType.JSON)
                .when().get("/accounts/health")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }
    
}