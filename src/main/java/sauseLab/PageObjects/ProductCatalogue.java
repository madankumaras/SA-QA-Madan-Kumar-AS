package sauseLab.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sauseLab.AbstractionComponents.basePage;

public class ProductCatalogue extends basePage {

    @FindBy(css = ".inventory_item")
    private List<WebElement> productList;

    public ProductCatalogue(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void addProductToCart(String productName) {
        for (WebElement product : productList) {
            String name = product.findElement(org.openqa.selenium.By.cssSelector(".inventory_item_name")).getText();
            if (name.equalsIgnoreCase(productName)) {
                product.findElement(org.openqa.selenium.By.cssSelector("button.btn_inventory")).click();
                break;
            }
        }
    }

    public void addProductsToCart(List<String> productNames) {
        for (String name : productNames) {
            addProductToCart(name);
        }
    }
}






