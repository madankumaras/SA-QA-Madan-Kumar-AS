package sauseLab.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;
import sauseLab.PageObjects.landingPage;
import sauseLab.PageObjects.ProductCatalogue;
import sauseLab.PageObjects.cartPage;
import sauseLab.PageObjects.checkOutOverviewPage;
import sauseLab.PageObjects.checkoutInfoPage;
import sauseLab.PageObjects.checkOutCompletePage;
import sauseLab.TestComponents.baseTest;
import com.aventstack.extentreports.ExtentTest;
import sauseLab.TestComponents.Listeners;

public class PlaceOrderTest extends baseTest {

	/**
 * This test case performs the following steps:
 * 1. Logs in as a standard user.
 * 2. Adds two specific products to the cart.
 * 3. Navigates to the cart and validates that both products are present.
 * 4. Proceeds to checkout, fills in user information, and submits.
 * 5. Validates that the products are listed on the checkout overview page.
 * 6. Completes the order and verifies the confirmation message.
 */
// Test Data
String userName = "standard_user";
String password = "secret_sauce";		

//Products to Add
String product1 = "Sauce Labs Backpack";
String product2 = "Sauce Labs Fleece Jacket";
String product3 = "Sauce Labs Bolt T-Shirt";
String product4 = "Sauce Labs Onesie";
String product5 = "Test.allTheThings() T-Shirt (Red)";
String product6 = "Sauce Labs Bike Light";

// Information to Checkout

String firstName = "Madan";
String lastName = "Kumar";
String postalCode = "560001";


    @Test(priority = 0)
    public void PlaceOrder() {
        ExtentTest logger = Listeners.extentTest.get();

        // --- Login ---
        logger.info("Logging in as user: " + userName);
        landingPage loginPage = this.landingPage;
        loginPage.login(userName, password);

        // NEW login verification!
        if (loginPage.isErrorVisible()) {
            String actualError = loginPage.getErrorMessage();
            logger.fail("Login failed! Error message: " + actualError);
            Assert.fail("Login failed: " + actualError);  
        } else {
            logger.pass("Login successful");
        }
        
        
        // --- Add Multiple Products to Cart ---
        List<String> productsToAdd = Arrays.asList(product1, product2, product3, product4);
        logger.info("Adding products to cart: " + productsToAdd);
        ProductCatalogue productCatalog = new ProductCatalogue(driver);

        for (String product : productsToAdd) {
            productCatalog.addProductToCart(product); 
            logger.info("Attempted to add product: " + product);
        }

        // Immediately get the cart contents as seen by UI (to verify)
        List<String> actualCartProducts = productCatalog.goToCartPage().getCartProductNames();

        if (!actualCartProducts.containsAll(productsToAdd)) {
            String cartAddError = "Not all products were added to cart. "
                + "Expected: " + productsToAdd + " | Actual: " + actualCartProducts;
            logger.fail(cartAddError);
            Assert.fail(cartAddError);  
        } else {
            logger.pass("All selected products added to cart successfully. Cart now contains: " + actualCartProducts);
        }
        
        

        
        // --- Go to Cart and Validate Products ---
        logger.info("Navigating to cart page.");
        cartPage cartPageObj = productCatalog.goToCartPage();
        List<String> cartProducts = cartPageObj.getCartProductNames();
        logger.info("Validating products in the cart: " + cartProducts);
        Assert.assertTrue(cartProducts.containsAll(productsToAdd), "All added products should be present in the cart!");
        if (!cartProducts.containsAll(productsToAdd)) {
            String cartError = "Cart does not contain all expected products. Expected: "
                    + productsToAdd + " | Actual: " + cartProducts;
            logger.fail(cartError);
            Assert.fail(cartError); 
        } else {
            logger.pass("Cart products validated successfully.");
        }
        
        
        
        // --- Checkout and Fill Info ---
        logger.info("Proceeding to checkout and entering user info.");
        checkoutInfoPage infoPage = cartPageObj.clickCheckout();
        checkOutOverviewPage overviewPage = infoPage.submitInfo(firstName, lastName, postalCode);

        if (infoPage.isErrorVisible()) { // or your own page's error detection
            logger.fail("Checkout info failed—Error: " + infoPage.getErrorMessage());
            Assert.fail("Checkout info form rejected: " + infoPage.getErrorMessage());
        } else {
            logger.pass("Checkout info submitted and Overview page loaded.");
        }

        
        
        

        // --- Validate Cart Items on Overview Page ---
        List<String> overviewProducts = overviewPage.getOverviewProductNames();
        logger.info("Validating products in checkout overview: " + overviewProducts);
        
        Assert.assertTrue(
            overviewPage.areItemsInOverview(productsToAdd),
            "All added products should be present on the overview page!"
        );
        if (!overviewPage.areItemsInOverview(productsToAdd)) {
            String overviewError = "Overview does not list all expected products. Expected: "
                    + productsToAdd + " | Actual: " + overviewProducts;
            logger.fail(overviewError);
            Assert.fail(overviewError);
        } else {
            logger.pass("Checkout overview products validated successfully.");
        }
        
        
        

        // --- Complete Order and Validate Confirmation Message ---
        logger.info("Completing order and validating confirmation.");
        checkOutCompletePage completePage = overviewPage.finishOrder();
        String confirmationHeader = completePage.getOrderConfirmationHeader();
        logger.info("Order confirmation message: " + confirmationHeader);
        Assert.assertTrue(
            confirmationHeader.contains("Thank you for your order!"),
            "Order confirmation message should appear!"
        );
        if (!confirmationHeader.contains("Thank you for your order!")) {
            String confirmError = "Order confirmation message not as expected! Actual: " + confirmationHeader;
            logger.fail(confirmError);
            Assert.fail(confirmError);
        } else {
            logger.pass("Order placed and confirmation message validated.");
        }
    }
    
    
}




