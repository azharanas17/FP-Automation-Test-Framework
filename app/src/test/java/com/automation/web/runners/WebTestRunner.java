package com.automation.web.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features/web",
    glue = {"com.automation.web.stepdefinitions"},
    plugin = {
        "pretty",
        "html:build/reports/cucumber/web-cucumber.html",
        "json:build/reports/cucumber/web-cucumber.json"
    },
    tags = "@web",
    monochrome = true
)
public class WebTestRunner {
}
