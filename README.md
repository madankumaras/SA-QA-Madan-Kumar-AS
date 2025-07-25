SauceDemo Selenium UI Automation
Overview
This project automates core end-to-end and negative scenarios for SauceDemo using the Selenium WebDriver, TestNG, and ExtentReports.
It follows the Page Object Model, integrates robust reporting (with logs and screenshots), and includes both positive and negative test flows.

Prerequisites
Java 11 or higher (tested with Java 21.0.6)
Maven (recommended for dependency management/build)
Browsers: Chrome (recommended, v100+), or any supported by Selenium
ChromeDriver compatible with your Chrome browser version
(handled by Selenium 4.x; no manual path config required in most cases)

Setup Instructions
1. Clone the repository
bash
git clone <your-repo-url>
cd <repo-folder>
2. Install dependencies with Maven
bash
mvn clean install
This downloads all required libraries (Selenium, TestNG, ExtentReports, etc.).

No OS-specific paths are hardcoded—all file operations use Java's platform-independent API.

How to Run the Tests
1. Single Unified Suite (Recommended)
The suite [FullRegressionSuite.xml] runs all end-to-end, negative, and cart scenarios in order.

Run from IDE (Eclipse/IntelliJ/VS Code):

Right-click FullRegressionSuite.xml → Run as → TestNG Suite

Or from Command Line/Maven:

bash
mvn test -DsuiteXmlFile=testng.xml
2. Run a Specific Feature/Scenario
Additional suite XMLs (e.g., negativeLoginSuite.xml, removeItemFromCartSuite.xml) are provided for targeted runs:

bash
mvn test -DsuiteXmlFile=removeItemFromCartSuite.xml
Where to Find Reports
1. HTML Report (ExtentReports)
Location:
text
<project-root>/reports/ExtentReport_<timestamp>.html
E.g., reports/ExtentReport_20250724_190100.html

Contents:
Fully detailed logs, pass/fail status, and screenshots of failures.

2. TestNG Default Reports
Location:

text
<project-root>/test-output/
Includes TestNG's HTML and XML summary.

3. Failure Screenshots
Attached inside Extent HTML reports under failed steps.

Project Structure
text
src/
  main/
    java/
      sauseLab/
        PageObjects/           # UI page objects (POM)
        AbstractionComponents/ # Base page/object utilities
        resources/             # Reporting (TestReport), Config
        TestComponents/        # BaseTest, Listeners, RetryAnalyzer
  test/
    java/
      sauseLab/
        tests/                 # Test classes (E2E, negative, cart, etc.)
reports/                       # Extent HTML reports (auto-created)
testng.xml                     # Main test suite
Key Features
Robust POM structure for maintainable automation

Extent HTML reports with full step logs and screenshots

Negative and positive regression flows covered

Easily extensible to add new scenarios or browser types

Example Command Recap
bash
# Run full regression suite
mvn test -DsuiteXmlFile=FullRegressionSuite.xml

# Run "Remove Item from Cart" scenario only
mvn test -DsuiteXmlFile=removeItemFromCartSuite.xml
Troubleshooting
Dependencies not resolving:
Ensure Maven is installed and mvn clean install is run.

Webdriver errors:
Ensure Chrome is installed and matches the detected ChromeDriver. Update browser or change the driver version if needed.

Reports not generated:
Make sure <project-root>/reports/ exists (created automatically), and extent.flush() is called (handled in listeners).

Author, Credits & Support
Authored by: Madan Kumar AS
For any questions, raise an issue, or contact madangowda8095@gmail.com / 6361306931.
