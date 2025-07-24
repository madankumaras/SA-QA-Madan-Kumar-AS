package sauseLab.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;

import sauseLab.PageObjects.landingPage;
import sauseLab.PageObjects.ProductCatalogue;
import sauseLab.PageObjects.cartPage;
import sauseLab.TestComponents.baseTest;
import com.aventstack.extentreports.ExtentTest;
import sauseLab.TestComponents.Listeners;

public class RemoveItemFromCartTest extends baseTest {

	// Test Data
	String userName = "standard_user";
	String password = "secret_sauce";
	
	
	String product1 = "Sauce Labs Backpack";
	String product2 = "Sauce Labs Fleece Jacket";

	@Test(priority = 1)
	public void removeProductFromCart() {
		ExtentTest logger = Listeners.extentTest.get();

		// --- Login ---
		logger.info("Logging in as user: " + userName);
		landingPage loginPage = this.landingPage;
		loginPage.login(userName, password);
		if (loginPage.isErrorVisible()) {
			String error = loginPage.getErrorMessage();
			logger.fail("Login failed: " + error);
			Assert.fail("Login failed: " + error);
		}
		logger.pass("Login successful.");

		// --- Add Multiple Products to Cart ---

		List<String> productsToAdd = Arrays.asList(product1, product2);
		logger.info("Adding products: " + productsToAdd);
		ProductCatalogue productCatalog = new ProductCatalogue(driver);
		productCatalog.addProductsToCart(productsToAdd);
		logger.pass("Products added to cart.");

		// --- Go to Cart Page and Remove Product ---
		cartPage cartPageObj = productCatalog.goToCartPage();
		logger.info("Removing '" + product1 + "' from the cart.");
		cartPageObj.removeProduct(product1); // Implements the removal

		// --- Validate Removed Product is No Longer in Cart
		List<String> cartProducts = cartPageObj.getCartProductNames();
		logger.info("Cart contents after removal: " + cartProducts);
		Assert.assertFalse(cartProducts.contains(product1), "Removed product should not be present in cart!");
		Assert.assertTrue(cartProducts.contains(product2), "Other product should still be in the cart!");
		logger.pass("Product removed successfully, remaining cart validated.");
	}
}
