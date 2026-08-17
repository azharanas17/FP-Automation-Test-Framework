package com.automation.api.stepdefinitions;

import com.automation.api.utils.ScenarioContext;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

public class UserSteps {

    @Then("the response should contain a user with valid data")
    public void theResponseShouldContainAUserWithValidData() {
        String id = ScenarioContext.getResponse().jsonPath().getString("data[0].id");
        String firstName = ScenarioContext.getResponse().jsonPath().getString("data[0].firstName");
        String lastName = ScenarioContext.getResponse().jsonPath().getString("data[0].lastName");
        assertNotNull("User ID should not be null", id);
        assertFalse("First name should not be empty", firstName.isEmpty());
        assertFalse("Last name should not be empty", lastName.isEmpty());
    }

    @Then("the response should contain detailed user data")
    public void theResponseShouldContainDetailedUserData() {
        String id = ScenarioContext.getResponse().jsonPath().getString("id");
        String firstName = ScenarioContext.getResponse().jsonPath().getString("firstName");
        String lastName = ScenarioContext.getResponse().jsonPath().getString("lastName");
        assertNotNull("User ID should not be null", id);
        assertFalse("First name should not be empty", firstName.isEmpty());
        assertFalse("Last name should not be empty", lastName.isEmpty());
    }

    @Then("the response should contain the created user with firstName {string}")
    public void theResponseShouldContainTheCreatedUserWithFirstName(String expectedFirstName) {
        String actualFirstName = ScenarioContext.getResponse().jsonPath().getString("firstName");
        assertEquals("First name should match", expectedFirstName, actualFirstName);
        String id = ScenarioContext.getResponse().jsonPath().getString("id");
        assertNotNull("Created user should have an id", id);
        ScenarioContext.setUserId(id);
    }

    @Then("the response should contain the updated user with firstName {string}")
    public void theResponseShouldContainTheUpdatedUserWithFirstName(String expectedFirstName) {
        String actualFirstName = ScenarioContext.getResponse().jsonPath().getString("firstName");
        assertEquals("First name should match", expectedFirstName, actualFirstName);
    }

    @Then("the error message should be {string}")
    public void theErrorMessageShouldBe(String expectedError) {
        String actualError = ScenarioContext.getResponse().jsonPath().getString("error");
        assertEquals("Error message should match", expectedError, actualError);
    }
}
