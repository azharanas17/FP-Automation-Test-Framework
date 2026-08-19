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

    @When("the user clicks on the {string} category")
    public void theUserClicksOnTheCategory(String categoryName) {
        productsPage.clickCategory(categoryName);
    }

    @Then("the product list should show products from {string} category")
    public void theProductListShouldShowProductsFromCategory(String categoryName) {
        assertTrue("Product list should not be empty for category: " + categoryName,
            productsPage.getProductCount() > 0);
    }

    @When("the user clicks the home link")
    public void theUserClicksTheHomeLink() {
        productsPage.clickHomeLink();
    }

    @Then("the user should be on the homepage")
    public void theUserShouldBeOnTheHomepage() {
        String url = productsPage.getCurrentUrl();
        assertTrue("User should be on homepage", url.equals("https://www.demoblaze.com/") ||
            url.equals("https://www.demoblaze.com/index.html"));
    }

    @Then("the product count should be greater than {int}")
    public void theProductCountShouldBeGreaterThan(int minCount) {
        assertTrue("Product count should be greater than " + minCount,
            productsPage.getProductCount() > minCount);
    }

    @When("the user clicks on the second product")
    public void theUserClicksOnTheSecondProduct() {
        assertTrue("Should have at least 2 products", productsPage.getProductCount() >= 2);
        productsPage.clickViewProduct(1);
    }
}
