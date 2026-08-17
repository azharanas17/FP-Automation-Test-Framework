package com.automation.api.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features/api",
    glue = {"com.automation.api.stepdefinitions"},
    plugin = {
        "pretty",
        "html:build/reports/cucumber/api-cucumber.html",
        "json:build/reports/cucumber/api-cucumber.json"
    },
    tags = "@api",
    monochrome = true
)
public class ApiTestRunner {
}
