package com.automation.web.stepdefinitions;

import com.automation.web.pages.LoginPage;
import com.automation.web.utils.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.Assert.*;

public class LoginSteps {

    private LoginPage loginPage;

    @Before
    public void setUp() {
        loginPage = new LoginPage();
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @When("the user clicks the login link")
    public void theUserClicksTheLoginLink() {
        loginPage.clickLoginLink();
    }

    @When("the user enters username {string} and password {string}")
    public void theUserEntersUsernameAndPassword(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("the user clicks the login button")
    public void theUserClicksTheLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("the user should be logged in successfully")
    public void theUserShouldBeLoggedInSuccessfully() {
        assertTrue("User should be logged in", loginPage.isLoggedIn());
    }

    @Then("an alert should appear with message {string}")
    public void anAlertShouldAppearWithMessage(String expectedMessage) {
        assertTrue("Alert should be present", loginPage.isAlertPresent());
        String actualMessage = loginPage.getAlertText();
        assertEquals("Alert message should match", expectedMessage, actualMessage);
        loginPage.acceptAlert();
    }

    @When("the user signs up with username {string} and password {string}")
    public void theUserSignsUpWithUsernameAndPassword(String username, String password) {
        loginPage.signup(username, password);
    }

    @Then("the signup alert should appear with message {string}")
    public void theSignupAlertShouldAppearWithMessage(String expectedMessage) {
        assertTrue("Alert should be present", loginPage.isAlertPresent());
        String actualMessage = loginPage.getAlertText();
        assertTrue("Alert message should be success or user already exists",
            actualMessage.contains("Sign up successful.") || actualMessage.contains("This user already exist."));
        loginPage.acceptAlert();
    }
}
