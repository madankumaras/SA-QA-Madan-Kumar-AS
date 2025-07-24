package sauseLab.TestComponents;

import com.aventstack.extentreports.Status;
import sauseLab.resources.TestReport;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import org.openqa.selenium.WebDriver;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Listeners extends baseTest implements ITestListener, IAnnotationTransformer {

    ExtentReports extent = TestReport.getReportObject();
    public static  ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = extentTest.get();
        test.fail(result.getThrowable());

        WebDriver driver = null;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            test.warning("Could not obtain WebDriver: " + e.getMessage());
        }

        if (driver != null) {
            try {
                String filePath = getScreenshot(result.getMethod().getMethodName(), driver);
                test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
            } catch (IOException e) {
                test.warning("Screenshot capture failed: " + e.getMessage());
            }
        } else {
            test.warning("Driver was null. Screenshot not captured.");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    // Automatic retry setup (global for all tests)
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(sauseLab.TestComponents.RetryAnalyzer.class);
    }
}
