package sauseLab.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

import sauseLab.AbstractionComponents.basePage;
import sauseLab.resources.Global_data;

public class landingPage extends basePage {

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginBtn;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMsgContainer;

    public landingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) {
        enterText(usernameInput, username);
        enterText(passwordInput, password);
        click(loginBtn);
    }

    public String getErrorMessage() {
        waitForWebElementToAppear(errorMsgContainer);
        return errorMsgContainer.getText();
    }
    
    public boolean isErrorVisible() {
        // Returns true if the errorMsgContainer is visible and contains text
        return isElementVisible(errorMsgContainer) && !getText(errorMsgContainer).isEmpty();
    }


    public void goTo() {
        try {
            Global_data gd = new Global_data();
            driver.get(gd.getBaseUrl());
        } catch (Exception e) {
            throw new RuntimeException("Base URL not found: " + e.getMessage());
        }
    }
}
