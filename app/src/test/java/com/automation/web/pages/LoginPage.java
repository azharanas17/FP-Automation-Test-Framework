package com.automation.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage extends BasePage {

    @FindBy(id="loginusername")
    private WebElement usernameField;

    @FindBy(id="loginpassword")
    private WebElement passwordField;

    @FindBy(xpath="//button[contains(text(),'Log in')]")
    private WebElement loginButton;

    @FindBy(id="nameofuser")
    private WebElement loggedInUser;

    @FindBy(id="sign-username")
    private WebElement signupUsernameField;

    @FindBy(id="sign-password")
    private WebElement signupPasswordField;

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
            WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            alertWait.until(ExpectedConditions.alertIsPresent());
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

    public void clickSignupLink() {
        wait.until(ExpectedConditions.elementToBeClickable(
            driver.findElement(org.openqa.selenium.By.id("signin2"))
        )).click();
    }

    public void enterSignupUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(signupUsernameField)).clear();
        signupUsernameField.sendKeys(username);
    }

    public void enterSignupPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(signupPasswordField)).clear();
        signupPasswordField.sendKeys(password);
    }

    public void clickSignupButton() {
        wait.until(ExpectedConditions.elementToBeClickable(
            driver.findElement(org.openqa.selenium.By.xpath("//button[contains(text(),'Sign up')]"))
        )).click();
    }

    public void signup(String username, String password) {
        clickSignupLink();
        enterSignupUsername(username);
        enterSignupPassword(password);
        clickSignupButton();
    }

    public void closeSignupModal() {
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
            "$('#signInModal').modal('hide'); $('.modal-backdrop').hide();"
        );
    }
}
