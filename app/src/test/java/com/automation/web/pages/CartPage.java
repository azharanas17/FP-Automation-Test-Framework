package com.automation.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class CartPage extends BasePage {

    @FindBy(css="#tbodyid tr")
    private List<WebElement> cartItems;

    @FindBy(css="#tbodyid td:nth-child(2)")
    private List<WebElement> cartItemNames;

    @FindBy(css="#tbodyid td:nth-child(3)")
    private List<WebElement> cartItemPrices;

    @FindBy(css="#totalp")
    private WebElement totalPrice;

    @FindBy(css=".btn-success")
    private WebElement placeOrderButton;

    @FindBy(css=".btn-danger")
    private List<WebElement> deleteButtons;

    public CartPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public void navigateTo() {
        driver.get("https://www.demoblaze.com/cart.html");
    }

    public int getCartItemCount() {
        return cartItems.size();
    }

    public boolean isCartItemPresent(String productName) {
        for (WebElement name : cartItemNames) {
            if (name.getText().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }

    public String getTotalPrice() {
        wait.until(ExpectedConditions.visibilityOf(totalPrice));
        return totalPrice.getText();
    }

    public void clickPlaceOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton)).click();
    }

    public void deleteCartItem(int index) {
        wait.until(ExpectedConditions.visibilityOfAllElements(deleteButtons));
        deleteButtons.get(index).click();
    }

    public boolean isCartEmpty() {
        return cartItems.isEmpty();
    }
}
