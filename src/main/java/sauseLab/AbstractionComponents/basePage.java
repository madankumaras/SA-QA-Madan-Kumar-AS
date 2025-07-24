package sauseLab.AbstractionComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import sauseLab.PageObjects.cartPage;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public abstract class basePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    // Constructor to initialize driver and wait
    public basePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    @FindBy(className = "shopping_cart_link")
    protected WebElement cartIcon;

    @FindBy(id = "react-burger-menu-btn")
    protected WebElement hamburgerMenuIcon;


    // Common method: Click element
    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    // Common method: Type text into field
    protected void enterText(WebElement element, String text) {
      wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    // Common method: Get text from element
    protected String getText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }

    // Wait for element using By (returns the WebElement)
    public WebElement waitForElementToAppear(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait for a WebElement directly (returns the same WebElement after visible)
    public WebElement waitForWebElementToAppear(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    
 // In BasePage or relevant parent page object

    public cartPage goToCartPage() {
        cartIcon.click(); // or cartHeader.click()
        return new cartPage(driver);
    }



    // Scroll to an element
    public void scrollToElement(WebElement element) {
      waitForWebElementToAppear(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Get attribute value
    public String getAttribute(WebElement element, String attribute) {
        waitForWebElementToAppear(element);
        return element.getAttribute(attribute);
    }
    
    protected boolean isElementVisible(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
