package com.automation.web.stepdefinitions;

import com.automation.web.pages.CartPage;
import com.automation.web.pages.CheckoutPage;
import com.automation.web.pages.ProductDetailPage;
import com.automation.web.pages.ProductsPage;
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

public class CartSteps {

    private ProductsPage productsPage;
    private ProductDetailPage productDetailPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @Before
    public void setUp() {
        productsPage = new ProductsPage();
        productDetailPage = new ProductDetailPage();
        cartPage = new CartPage();
        checkoutPage = new CheckoutPage();
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @When("the user adds the product to cart")
    public void theUserAddsTheProductToCart() {
        productDetailPage.clickAddToCart();
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        DriverFactory.getDriver().switchTo().alert().accept();
    }

    @Then("the product should be added to the cart successfully")
    public void theProductShouldBeAddedToTheCartSuccessfully() {
        productsPage.clickCartLink();
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(org.openqa.selenium.By.cssSelector("#tbodyid tr")));
        assertTrue("Cart should contain at least 1 item", cartPage.getCartItemCount() > 0);
    }

    @When("the user navigates to the cart page")
    public void theUserNavigatesToTheCartPage() {
        productsPage.clickCartLink();
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(org.openqa.selenium.By.cssSelector("#tbodyid tr")));
    }

    @Then("the cart should contain at least {int} item")
    public void theCartShouldContainAtLeastItem(int minCount) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(org.openqa.selenium.By.cssSelector("#tbodyid tr")));
        assertTrue("Cart should contain at least " + minCount + " item(s)",
            cartPage.getCartItemCount() >= minCount);
    }

    @When("the user clicks place order")
    public void theUserClicksPlaceOrder() {
        cartPage.clickPlaceOrder();
    }

    @When("the user fills checkout form with name {string}, country {string}, city {string}, card {string}, month {string}, year {string}")
    public void theUserFillsCheckoutForm(String name, String country, String city, String card, String month, String year) {
        checkoutPage.fillCheckoutForm(name, country, city, card, month, year);
    }

    @When("the user clicks purchase")
    public void theUserClicksPurchase() {
        checkoutPage.clickPurchase();
    }

    @Then("the confirmation message should be displayed")
    public void theConfirmationMessageShouldBeDisplayed() {
        String message = checkoutPage.getConfirmationMessage();
        assertNotNull("Confirmation message should be displayed", message);
        assertFalse("Confirmation message should not be empty", message.isEmpty());
    }

    @When("the user adds {int} products to cart")
    public void theUserAddsProductsToCart(int count) {
        for (int i = 0; i < count; i++) {
            productsPage.clickViewProduct(i);
            productDetailPage.clickAddToCart();
            WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent());
            DriverFactory.getDriver().switchTo().alert().accept();
            productsPage.clickHomeLink();
        }
    }

    @Then("the cart should contain {int} items")
    public void theCartShouldContainItems(int expectedCount) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(org.openqa.selenium.By.cssSelector("#tbodyid tr")));
        assertEquals("Cart should contain " + expectedCount + " items", expectedCount, cartPage.getCartItemCount());
    }

    @Then("the cart total price should be displayed")
    public void theCartTotalPriceShouldBeDisplayed() {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(org.openqa.selenium.By.cssSelector("#totalp")));
        String totalPrice = cartPage.getTotalPrice();
        assertNotNull("Total price should be displayed", totalPrice);
        assertFalse("Total price should not be empty", totalPrice.isEmpty());
    }

    @When("the user deletes the first item from the cart")
    public void theUserDeletesTheFirstItemFromTheCart() {
        cartPage.deleteCartItem(0);
    }

    @Then("the cart should be empty")
    public void theCartShouldBeEmpty() {
        assertTrue("Cart should be empty", cartPage.isCartEmpty());
    }
}
