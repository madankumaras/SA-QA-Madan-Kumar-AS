🚀 SauceDemo Selenium UI Automation
This project automates end-to-end and negative test scenarios for SauceDemo using:

🧪 Selenium WebDriver

🧪 TestNG

📊 ExtentReports

🧱 Page Object Model (POM)

Includes detailed HTML reports with screenshots, modular page classes, and robust failure handling.

📦 Prerequisites
Java: 11 or higher (✅ tested with Java 21.0.6)

Maven: Installed & added to path

Chrome Browser: Version 100+

ChromeDriver: Automatically managed by Selenium 4+

✅ No OS-specific paths used — cross-platform compatible (Windows/Linux/macOS)

🛠️ Setup Instructions
1. Clone the Repository
bash
git clone https://github.com/<your-username>/<your-repo>.git
cd <repo-folder>
2. Install Dependencies
bash
mvn clean install
This will download all required libraries: Selenium, TestNG, ExtentReports, etc.

▶️ How to Run the Tests
🔹 Option 1: Run Full Test Suite (Recommended)
Using IDE (Eclipse/IntelliJ):

**Right-click FullRegressionSuite.xml → Run as → TestNG Suite**

Using Maven:
bash
mvn test -DsuiteXmlFile=FullRegressionSuite.xml
🔹 Option 2: Run Specific Feature/Scenario
bash
mvn test -DsuiteXmlFile=removeItemFromCartSuite.xml
Other examples:

negativeLogin.xml
PlacrOrder.xml

🌐 Browser Configuration
This framework supports Chrome, Firefox, and Edge.

You can easily change the browser used for test execution by updating a config property — no code changes required!

🔧 Steps:
Go to:
src/main/java/sauseLab/resources/Globaldata.properties

Modify the browser key:
properties
**browser=chrome**
✅ Supported values:

**chrome
firefox
edge**

ℹ️ This value is read automatically by Global_data.java and used during browser initialization in your tests.

📁 Files Involved:
Globaldata.properties — defines which browser to run

Global_data.java — loads and provides the config to test classes

**📂 Reports & Outputs**
✅ 1. Extent HTML Report
Location: reports/ExtentReport_<timestamp>.html

Contents: Pass/fail logs, screenshots, and full test steps

**✅ 2. TestNG Default Report**
Location: test-output/index.html

Includes TestNG's default HTML and XML summaries

**✅ 3. Screenshots (Failures)**
Auto-attached in Extent HTML reports under failed test steps

🗂️ Project Structure
bash
src/
├── main/
│   └── java/
│       └── sauseLab/
│           ├── PageObjects/           # Page Object Model classes
│           ├── AbstractionComponents/ # Base page
│           ├── resources/             # Reporting, configs
│           └── TestComponents/        # BaseTest, Listeners, Retry logic
├── test/
│   └── java/
│       └── sauseLab/tests/            # TestNG test classes
reports/                               # Auto-generated reports
testng.xml                             # Master suite file
🔑 Key Features
✅ Modular POM design for scalability

✅ Detailed HTML reports with screenshots

✅ Negative + positive scenario coverage

✅ Retry logic, listeners, reusable base classes

✅ Easily extendable and cross-platform setup

🧪 Example Commands Recap
bash
# Run full regression suite
mvn test -DsuiteXmlFile=FullRegressionSuite.xml

# Run individual test suite
mvn test -DsuiteXmlFile=removeItemFromCartSuite.xml
🛠️ Troubleshooting
Issue	Fix
Maven dependencies missing	Ensure Maven is installed and run mvn clean install
WebDriver version mismatch	Ensure Chrome version matches Selenium-managed ChromeDriver
Reports not generating	Make sure extent.flush() is called (handled in listeners)
Screenshots not saved	Ensure valid WebDriver instance and screenshot folder exists

👨‍💻 Author & Support
Authored by: Madan Kumar AS
📧 Email: madangowda8095@gmail.com
📞 Contact: +91 6361306931

For issues, raise a GitHub Issue or reach out directly.
