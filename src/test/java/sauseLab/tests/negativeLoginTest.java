package sauseLab.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import sauseLab.PageObjects.landingPage;
import sauseLab.TestComponents.baseTest;
import sauseLab.TestComponents.Listeners;

public class negativeLoginTest extends baseTest {
	
	String userName = "locked_out_user";
	String password = "secret_sauce";
	String Wrong_psw = "wrong_password";

    /**
     * Negative login test: attempts login with a locked out user
     * and checks for proper error message
     */
    @Test(priority = 3)
    public void negativeLogin_lockedOutUser() {
        ExtentTest logger = Listeners.extentTest.get();
        landingPage loginPage = this.landingPage;

     

        logger.info("Attempting login with locked out user: " + userName);
        loginPage.login(userName, password);

        if (loginPage.isErrorVisible()) {
            String errorMsg = loginPage.getErrorMessage();
            logger.pass("Proper error shown: " + errorMsg);
            Assert.assertTrue(
                errorMsg.toLowerCase().contains("locked out"),
                "Locked out error message should be displayed."
            );
        } else {
            logger.fail("No error message shown for locked out user!");
            Assert.fail("Login error message not displayed for locked out user.");
        }
    }

    /**
     * Negative login test: attempts login with invalid password.
     */
    @Test(priority = 2)
    public void negativeLogin_invalidPassword() {
        ExtentTest logger = Listeners.extentTest.get();
        landingPage loginPage = this.landingPage;

        

        logger.info("Attempting login with bad password for user: " + userName);
        loginPage.login(userName, Wrong_psw);

        if (loginPage.isErrorVisible()) {
            String errorMsg = loginPage.getErrorMessage();
            logger.pass("Proper error shown: " + errorMsg);
            Assert.assertTrue(
                errorMsg.toLowerCase().contains("epic sadface"),
                "Expected error message for invalid credentials."
            );
        } else {
            logger.fail("No error message shown for invalid password!");
            Assert.fail("Login error message not displayed for invalid password.");
        }
    }
}
