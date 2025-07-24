package sauseLab.resources;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class TestReport {

    private static ExtentReports extent;
    
    public static ExtentReports getReportObject() {
    	
    	
        if (extent == null) {
        	
        	String browserName = "";

            // 1. Create /reports directory if not exists
            String reportsDirPath = Paths.get(System.getProperty("user.dir"), "reports").toString();
            File dir = new File(reportsDirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 2. Create timestamped report file
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String reportFilePath = Paths.get(reportsDirPath, "ExtentReport_" + timestamp + ".html").toString();

            // 3. Set up the Spark Reporter
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFilePath);
            sparkReporter.config().setDocumentTitle("Test Execution Report");
            sparkReporter.config().setReportName("SauceDemo Automation Results");
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setEncoding("UTF-8");
            sparkReporter.viewConfigurer().viewOrder()
                    .as(new ViewName[]{
                            ViewName.DASHBOARD,
                            ViewName.TEST,
                            ViewName.CATEGORY,
                            ViewName.AUTHOR,
                            ViewName.EXCEPTION,
                            ViewName.LOG
                    }).apply();

            // 4. Create ExtentReports and attach the reporter
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            try {
				Global_data globalData = new Global_data();
				 browserName = globalData.getBrowser();
			} catch (IOException e) {
		
				e.printStackTrace();
			}
            // 5. System info
            extent.setSystemInfo("Tester", "Madan Kumar AS");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("Browser",  browserName); 

            System.out.println("Extent report created at: " + reportFilePath);
        }

        return extent;
    }
}
