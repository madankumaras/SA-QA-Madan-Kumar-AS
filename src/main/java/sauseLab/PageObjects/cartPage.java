package sauseLab.PageObjects;


import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sauseLab.AbstractionComponents.basePage;

public class cartPage extends basePage {

    @FindBy(css = ".cart_item") // Selects all products in the cart
    private List<WebElement> cartItems;

    @FindBy(id = "checkout") // Checkout button
    private WebElement checkoutButton;

    public cartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Returns a list of product names present in the cart
    public List<String> getCartProductNames() {
        List<String> names = new ArrayList<>();
        for (WebElement item : cartItems) {
            // Within each .cart_item get .inventory_item_name
            String name = item.findElement(By.cssSelector(".inventory_item_name")).getText().trim();
            names.add(name);
        }
        return names;
    }

    
    public List<String> getCartProductPrices() {
        List<String> prices = new ArrayList<>();
        for (WebElement item : cartItems) {
            String price = item.findElement(By.cssSelector(".inventory_item_price")).getText().trim();
            prices.add(price);
        }
        return prices;
    }

    // Proceeds to the Checkout page and returns its object
    public checkoutInfoPage clickCheckout() {
        checkoutButton.click();
        return new checkoutInfoPage(driver);
    }

    // Assertion helper: validate all expected items are in the cart
    public boolean areItemsInCart(List<String> expectedItems) {
        List<String> actual = getCartProductNames();
        return actual.containsAll(expectedItems) && expectedItems.containsAll(actual);
    }
    
    public void removeProduct(String productName) {
        List<WebElement> items = driver.findElements(By.cssSelector(".cart_item"));
        for (WebElement item : items) {
            String name = item.findElement(By.cssSelector(".inventory_item_name")).getText();
            if (name.equalsIgnoreCase(productName)) {
                WebElement removeBtn = item.findElement(By.cssSelector("button.cart_button"));
                removeBtn.click();
                break;
            }
        }
    }
}
