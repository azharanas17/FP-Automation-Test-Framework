package com.automation.api.clients;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiClient {

    private static final String BASE_URL = "https://dummyapi.io/data/v1";
    private static final String APP_ID = "63a804408eb0cb069b57e43a";

    private RequestSpecification getRequest() {
        return RestAssured.given()
            .baseUri(BASE_URL)
            .header("app-id", APP_ID)
            .header("Content-Type", "application/json");
    }

    public Response get(String endpoint) {
        return getRequest()
            .when()
            .get(endpoint)
            .then()
            .extract()
            .response();
    }

    public Response post(String endpoint, String body) {
        return getRequest()
            .body(body)
            .when()
            .post(endpoint)
            .then()
            .extract()
            .response();
    }

    public Response put(String endpoint, String body) {
        return getRequest()
            .body(body)
            .when()
            .put(endpoint)
            .then()
            .extract()
            .response();
    }

    public Response delete(String endpoint) {
        return getRequest()
            .when()
            .delete(endpoint)
            .then()
            .extract()
            .response();
    }
}
