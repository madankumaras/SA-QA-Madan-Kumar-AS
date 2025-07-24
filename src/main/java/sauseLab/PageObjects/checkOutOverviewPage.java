package sauseLab.PageObjects;


import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sauseLab.AbstractionComponents.basePage;

public class checkOutOverviewPage extends basePage {

    @FindBy(css = ".cart_item") // List of products in the overview
    private List<WebElement> overviewItems;

    @FindBy(id = "finish")
    private WebElement finishButton;

    public checkOutOverviewPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Returns a list of product names in the overview summary
    public List<String> getOverviewProductNames() {
        List<String> names = new ArrayList<>();
        for (WebElement item : overviewItems) {
            String name = item.findElement(By.cssSelector(".inventory_item_name")).getText().trim();
            names.add(name);
        }
        return names;
    }

    // Proceed to order completion
    public checkOutCompletePage finishOrder() {
        finishButton.click();
        return new checkOutCompletePage(driver);
    }

   
    public boolean areItemsInOverview(List<String> expectedItems) {
        List<String> actual = getOverviewProductNames();
        return actual.containsAll(expectedItems) && expectedItems.containsAll(actual);
    }
}
