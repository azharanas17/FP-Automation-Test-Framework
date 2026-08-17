package com.automation.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {

    @FindBy(id="name")
    private WebElement nameField;

    @FindBy(id="country")
    private WebElement countryField;

    @FindBy(id="city")
    private WebElement cityField;

    @FindBy(id="card")
    private WebElement cardField;

    @FindBy(id="month")
    private WebElement monthField;

    @FindBy(id="year")
    private WebElement yearField;

    @FindBy(xpath="//button[contains(text(),'Purchase')]")
    private WebElement purchaseButton;

    @FindBy(css=".sweet-alert h2")
    private WebElement confirmationMessage;

    @FindBy(css=".confirm")
    private WebElement confirmButton;

    public CheckoutPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public void enterName(String name) {
        wait.until(ExpectedConditions.visibilityOf(nameField)).sendKeys(name);
    }

    public void enterCountry(String country) {
        countryField.sendKeys(country);
    }

    public void enterCity(String city) {
        cityField.sendKeys(city);
    }

    public void enterCard(String card) {
        cardField.sendKeys(card);
    }

    public void enterMonth(String month) {
        monthField.sendKeys(month);
    }

    public void enterYear(String year) {
        yearField.sendKeys(year);
    }

    public void clickPurchase() {
        wait.until(ExpectedConditions.elementToBeClickable(purchaseButton)).click();
    }

    public String getConfirmationMessage() {
        wait.until(ExpectedConditions.visibilityOf(confirmationMessage));
        return confirmationMessage.getText();
    }

    public void fillCheckoutForm(String name, String country, String city, String card, String month, String year) {
        enterName(name);
        enterCountry(country);
        enterCity(city);
        enterCard(card);
        enterMonth(month);
        enterYear(year);
    }

    public void confirmPurchase() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton)).click();
    }
}
