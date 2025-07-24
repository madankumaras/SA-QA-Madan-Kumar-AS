package sauseLab.TestComponents;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.edge.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import sauseLab.PageObjects.landingPage;
import sauseLab.resources.Global_data;

public class baseTest {
    protected landingPage landingPage;
    public WebDriver driver;

    // Initialize the WebDriver based on browser property
    public WebDriver initializeDriver() throws IOException {
        Global_data globalData = new Global_data();
        String browserName = globalData.getBrowser().toLowerCase();

        if (browserName.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();

            //  Disable Chrome password manager popup
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", prefs);

            //  Other options to suppress infobars & enable clean automation
            options.addArguments("--disable-features=PasswordManagerOnboarding,PasswordManagerRedesign");
            options.addArguments("--disable-save-password-bubble");
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation", "load-extension"});
            options.setExperimentalOption("useAutomationExtension", false);

            //  Headless mode
            if (browserName.contains("headless")) {
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
            }

            driver = new ChromeDriver(options);

        } else if (browserName.contains("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            if (browserName.contains("headless")) {
                options.addArguments("-headless");
            }
            driver = new FirefoxDriver(options);

        } else if (browserName.contains("edge")) {
            EdgeOptions options = new EdgeOptions();
            if (browserName.contains("headless")) {
                options.addArguments("headless");
            }
            driver = new EdgeDriver(options);

        } else {
            throw new IllegalArgumentException("Browser not supported: " + browserName);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    // Capture screenshot for reporting/debugging
    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "\\reports" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "\\reports" + testCaseName + ".png";
		
		
	}


    // Setup before every test
    @BeforeMethod(alwaysRun = true)
    public landingPage launchApplication() throws IOException {
        driver = initializeDriver();
        landingPage = new landingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    //  Cleanup after test
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
