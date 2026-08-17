package com.automation.api.stepdefinitions;

import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class ApiHooks {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://dummyapi.io/data/v1";
    }
}
