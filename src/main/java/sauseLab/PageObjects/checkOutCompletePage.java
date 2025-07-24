package sauseLab.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sauseLab.AbstractionComponents.basePage;

public class checkOutCompletePage extends basePage {

    @FindBy(css = "[data-test='complete-header']")
    private WebElement completeHeader;

    @FindBy(css = "[data-test='complete-text']")
    private WebElement completeText;

    @FindBy(id = "back-to-products")
    private WebElement backHomeButton;

    public checkOutCompletePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Get the main confirmation text (e.g. "Thank you for your order!")
    public String getOrderConfirmationHeader() {
        return getText(completeHeader);
    }

    // Get the sub-text (e.g. "Your order has been dispatched...")
    public String getOrderConfirmationText() {
        return getText(completeText);
    }

    // Navigate back to products
    public ProductCatalogue backToHome() {
        click(backHomeButton);
        return new ProductCatalogue(driver);
    }
}
