**Weather Shopper Automation**
This project automates the Weather Shopper website using Selenium WebDriver with Cucumber for behavior-driven development (BDD) and ExtentReports for reporting.

**Project Structure**
package parallelism: Contains the main test step definitions using Cucumber.
Assignment_4: Contains configuration and page object model (POM) classes for interacting with the Weather Shopper website.
config: Configuration class to manage browser and Selenium Grid settings.
**Prerequisites**
Java: Ensure that Java is installed on your machine.
Maven: Install Maven for dependency management.
Selenium Grid: Set up Selenium Grid for remote WebDriver executions.
ExtentReports: Used for generating detailed test reports.

Place the appropriate WebDriver executables in the specified directory or set them in the system path.
Update config.json with the correct paths and settings for your setup.
**Install Dependencies:**
**Configure the Selenium Grid:**
Ensure that your Selenium Grid hub is running and accessible.

**Execute Tests:**
Use Maven to run the Cucumber tests:
bash
Copy code
mvn test
Generate Reports:
After execution, check the extent-reports directory for the test report named extent-report.html.

**Features
Dynamic Testing:**
Shop for moisturizers if the temperature is below 19°C.
Shop for sunscreens if the temperature is above 34°C.
**Automated Cart Actions:**
Add least expensive moisturizers and sunscreens to the cart based on specific conditions.
**Automated Checkout:**
Simulate payment using Stripe test card numbers and verify the payment outcome.
**Parallel Execution:**
Supports running tests in parallel using Selenium Grid.
Configuration
config.json: Contains settings for the browser type, Selenium Grid URL, and paths to driver executables.
credentials.json: Stores payment details such as email, card number, expiry, CVV, and zip code for checkout simulation.
**Page Object Model (POM)**
HomePage: Manages interactions on the homepage, such as checking temperature and navigating to specific sections.
MoisturizersPage: Handles actions related to selecting and verifying moisturizers.
SunscreensPage: Manages sunscreen selections and verification.
CartPage: Interacts with the shopping cart to add items and verify contents.
CheckoutPage: Manages the checkout process, including entering payment details and verifying outcomes.
**ExtentReports**
**Initialization:**
The ExtentSparkReporter is initialized with a specified report path, creating the report directory if it doesn't exist.
Test Reporting:
Tests are logged with information, pass, and fail statuses using ExtentReports.
