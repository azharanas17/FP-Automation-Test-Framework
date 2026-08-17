package com.automation.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(id="loginusername")
    private WebElement usernameField;

    @FindBy(id="loginpassword")
    private WebElement passwordField;

    @FindBy(xpath="//button[contains(text(),'Log in')]")
    private WebElement loginButton;

    @FindBy(id="nameofuser")
    private WebElement loggedInUser;

    public LoginPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public void navigateTo() {
        driver.get("https://www.demoblaze.com/");
    }

    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(
            driver.findElement(org.openqa.selenium.By.id("login2"))
        )).click();
    }

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameField)).clear();
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField)).clear();
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public boolean isLoggedIn() {
        try {
            wait.until(ExpectedConditions.visibilityOf(loggedInUser));
            return loggedInUser.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getLoggedInUsername() {
        return loggedInUser.getText();
    }

    public void login(String username, String password) {
        clickLoginLink();
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }
}
