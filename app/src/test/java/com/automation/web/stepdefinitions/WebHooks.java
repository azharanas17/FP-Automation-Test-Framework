package com.automation.web.stepdefinitions;

import com.automation.web.pages.ProductsPage;
import com.automation.web.utils.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;

public class WebHooks {

    private ProductsPage productsPage;

    @Before
    public void setUp() {
        productsPage = new ProductsPage();
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Given("the user is on the Demoblaze homepage")
    public void theUserIsOnTheDemoblazeHomepage() {
        productsPage.navigateTo();
    }
}
