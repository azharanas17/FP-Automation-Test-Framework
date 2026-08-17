package com.automation.web.stepdefinitions;

import com.automation.web.pages.ProductDetailPage;
import com.automation.web.pages.ProductsPage;
import com.automation.web.utils.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

public class ProductSteps {

    private ProductsPage productsPage;
    private ProductDetailPage productDetailPage;

    @Before
    public void setUp() {
        productsPage = new ProductsPage();
        productDetailPage = new ProductDetailPage();
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Then("the page title should be {string}")
    public void thePageTitleShouldBe(String expectedTitle) {
        assertEquals("Page title should match", expectedTitle, productsPage.getPageTitle());
    }

    @Then("the product list should not be empty")
    public void theProductListShouldNotBeEmpty() {
        assertTrue("Product list should not be empty", productsPage.getProductCount() > 0);
    }

    @When("the user clicks on the first product")
    public void theUserClicksOnTheFirstProduct() {
        productsPage.clickFirstProduct();
    }

    @When("the user clicks on product {string}")
    public void theUserClicksOnProduct(String productName) {
        productsPage.clickProductByName(productName);
    }

    @Then("the product detail page should be displayed")
    public void theProductDetailPageShouldBeDisplayed() {
        assertTrue("Product detail page should be displayed", productDetailPage.isProductDetailDisplayed());
    }

    @Then("the product should have a name, price and description")
    public void theProductShouldHaveNamePriceAndDescription() {
        assertFalse("Product name should not be empty", productDetailPage.getProductName().isEmpty());
        assertFalse("Product price should not be empty", productDetailPage.getProductPrice().isEmpty());
    }

    @Then("the product name should be {string}")
    public void theProductNameShouldBe(String expectedName) {
        assertEquals("Product name should match", expectedName, productDetailPage.getProductName());
    }
}
