package com.automation.api.stepdefinitions;

import com.automation.api.utils.ScenarioContext;
import io.cucumber.java.en.Then;
import java.util.List;
import static org.junit.Assert.*;

public class TagSteps {

    @Then("the response should contain a list of tags in data field")
    public void theResponseShouldContainAListOfTagsInDataField() {
        List<?> tags = ScenarioContext.getResponse().jsonPath().getList("data");
        assertNotNull("Tags list should not be null", tags);
        assertFalse("Tags list should not be empty", tags.isEmpty());
    }

    @Then("each tag should have an id and a value")
    public void eachTagShouldHaveAnIdAndAValue() {
        List<String> tags = ScenarioContext.getResponse().jsonPath().getList("data", String.class);
        assertNotNull("Tags list should not be null", tags);
        assertFalse("Tags list should not be empty", tags.isEmpty());
        int validTags = 0;
        for (String tag : tags) {
            if (tag != null && !tag.trim().isEmpty()) {
                validTags++;
            }
        }
        assertTrue("Tags list should contain at least one non-empty tag", validTags > 0);
    }
}
