package com.automation.api.stepdefinitions;

import com.automation.api.clients.ApiClient;
import com.automation.api.utils.ScenarioContext;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import java.util.UUID;
import static org.junit.Assert.*;

public class CommonApiSteps {

    private final ApiClient apiClient = new ApiClient();

    @Given("the API base URL is set")
    public void theApiBaseUrlIsSet() {
        assertTrue("API Client should be initialized", apiClient != null);
    }

    @When("I send a GET request to {string}")
    public void iSendAGetRequestTo(String endpoint) {
        Response res = apiClient.get(endpoint);
        ScenarioContext.setResponse(res);
    }

    @When("I send a POST request to {string} with body:")
    public void iSendAPostRequestToWithBody(String endpoint, String body) {
        String uniqueEmail = "user_" + UUID.randomUUID().toString().substring(0, 8) + "@test.com";
        body = body.replaceAll("\"email\"\\s*:\\s*\"[^\"]*\"", "\"email\":\"" + uniqueEmail + "\"");
        Response res = apiClient.post(endpoint, body);
        ScenarioContext.setResponse(res);
    }

    @When("I send a PUT request to {string} with body:")
    public void iSendAPutRequestToWithBody(String endpoint, String body) {
        Response res = apiClient.put(endpoint, body);
        ScenarioContext.setResponse(res);
    }

    @When("I send a DELETE request to {string}")
    public void iSendADeleteRequestTo(String endpoint) {
        Response res = apiClient.delete(endpoint);
        ScenarioContext.setResponse(res);
    }

    @When("I extract the user ID from the response")
    public void iExtractTheUserIdFromTheResponse() {
        String id = ScenarioContext.getResponse().jsonPath().getString("data[0].id");
        assertNotNull("User ID should not be null", id);
        ScenarioContext.setUserId(id);
    }

    @When("I send a GET request to the extracted user by ID")
    public void iSendAGetRequestToTheExtractedUserById() {
        String id = ScenarioContext.getUserId();
        assertNotNull("User ID should be extracted first", id);
        Response res = apiClient.get("/user/" + id);
        ScenarioContext.setResponse(res);
    }

    @When("I send a PUT request to the extracted user ID with body:")
    public void iSendAPutRequestToTheExtractedUserIdWithBody(String body) {
        String id = ScenarioContext.getUserId();
        assertNotNull("User ID should be extracted first", id);
        Response res = apiClient.put("/user/" + id, body);
        ScenarioContext.setResponse(res);
    }

    @When("I delete the newly created user")
    public void iDeleteTheNewlyCreatedUser() {
        String id = ScenarioContext.getResponse().jsonPath().getString("id");
        assertNotNull("Created user ID should not be null", id);
        Response res = apiClient.delete("/user/" + id);
        ScenarioContext.setResponse(res);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        assertEquals("Status code should match", expectedStatusCode, ScenarioContext.getResponse().getStatusCode());
    }
}
