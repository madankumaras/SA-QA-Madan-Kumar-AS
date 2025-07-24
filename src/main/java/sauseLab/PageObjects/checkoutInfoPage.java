package sauseLab.PageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sauseLab.AbstractionComponents.basePage;

public class checkoutInfoPage extends basePage {

    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(css = ".error-message-container")
    private WebElement errorMsgContainer; 
    
    public checkoutInfoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Fills in the info and proceeds to the next step
    public checkOutOverviewPage submitInfo(String firstName, String lastName, String postalCode) {
        enterText(firstNameInput, firstName);
        enterText(lastNameInput, lastName);
        enterText(postalCodeInput, postalCode);
        click(continueButton);
        return new checkOutOverviewPage(driver);
    }

    
    public boolean isErrorVisible() {
        return isElementVisible(errorMsgContainer) && !getText(errorMsgContainer).isEmpty();
    }

    public String getErrorMessage() {
        if (isElementVisible(errorMsgContainer)) {
            return getText(errorMsgContainer);
        }
        return "";
    }
}
