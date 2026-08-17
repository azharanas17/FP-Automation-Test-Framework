package com.automation.web.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class ProductsPage extends BasePage {

    @FindBy(css=".card-title a")
    private List<WebElement> productNames;

    @FindBy(css=".card h4")
    private List<WebElement> productPrices;

    @FindBy(css=".card .card-block .price")
    private List<WebElement> productPriceElements;

    @FindBy(css=".card a.btn.btn-default")
    private List<WebElement> viewProductButtons;

    @FindBy(css="#navbarExample a.nav-link")
    private List<WebElement> navLinks;

    @FindBy(css=".carousel-inner .active")
    private WebElement activeCarousel;

    @FindBy(id="cartur")
    private WebElement cartLink;

    public ProductsPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public void navigateTo() {
        driver.get("https://www.demoblaze.com/");
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public List<WebElement> getProductNames() {
        return productNames;
    }

    public String getFirstProductName() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productNames));
        return productNames.get(0).getText();
    }

    public void clickFirstProduct() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productNames));
        productNames.get(0).click();
    }

    public void clickProductByName(String name) {
        wait.until(ExpectedConditions.visibilityOfAllElements(productNames));
        for (WebElement product : productNames) {
            if (product.getText().equalsIgnoreCase(name)) {
                product.click();
                return;
            }
        }
    }

    public void clickViewProduct(int index) {
        wait.until(ExpectedConditions.visibilityOfAllElements(viewProductButtons));
        viewProductButtons.get(index).click();
    }

    public void clickCartLink() {
        wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
    }

    public int getProductCount() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productNames));
        return productNames.size();
    }

    public boolean isProductDisplayed(String productName) {
        for (WebElement product : productNames) {
            if (product.getText().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }
}
