package com.automation.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductDetailPage extends BasePage {

    @FindBy(css=".name")
    private WebElement productName;

    @FindBy(css=".price-container")
    private WebElement productPrice;

    @FindBy(css=".description")
    private WebElement productDescription;

    @FindBy(css=".btn-success")
    private WebElement addToCartButton;

    @FindBy(css="#more-information")
    private WebElement moreInfoSection;

    public ProductDetailPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public String getProductName() {
        return wait.until(ExpectedConditions.visibilityOf(productName)).getText();
    }

    public String getProductPrice() {
        return wait.until(ExpectedConditions.visibilityOf(productPrice)).getText();
    }

    public String getProductDescription() {
        wait.until(ExpectedConditions.visibilityOf(productDescription));
        return productDescription.getText();
    }

    public void clickAddToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }

    public boolean isProductDetailDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(productName));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
