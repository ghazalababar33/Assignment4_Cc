package parallel;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.*;
import pages.config;

public class BaseClassSteps {
    private WebDriver driver;
    private HomePage homePage;
    private MoisturizersPage moisturizersPage;
    private SunscreensPage sunscreensPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @SuppressWarnings("deprecation")
    private WebDriver initializeDriver(String browser) throws MalformedURLException {
        String hubUrl = config.getGridUrl(); // Use config class for URL
        if (hubUrl == null || hubUrl.isEmpty()) {
            throw new MalformedURLException("Grid URL is null or empty");
        }
        System.out.println("Initializing driver with browser: " + browser);
        System.out.println("Grid URL: " + hubUrl);
        if (browser.equals("chrome")) {
            return new RemoteWebDriver(new URL(hubUrl), new ChromeOptions());
        } else if (browser.equals("firefox")) {
            return new RemoteWebDriver(new URL(hubUrl), new FirefoxOptions());
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    @Given("I am on the Weather Shopper homepage")
    public void homepage() throws MalformedURLException {
        driver = initializeDriver(config.getBrowser());
        driver.get("https://weathershopper.pythonanywhere.com/");
        homePage = new HomePage(driver);
    }

    @Given("I am on the sunscreens page")
    public void sunscreen_shopper_homepage() throws MalformedURLException {
        driver = initializeDriver(config.getBrowser());
        driver.get("https://weathershopper.pythonanywhere.com/sunscreen");
        sunscreensPage = new SunscreensPage(driver);
    }

    @Given("I am on the moisturizers page")
    public void moisturizer_homepage() throws MalformedURLException {
        driver = initializeDriver(config.getBrowser());
        driver.get("https://weathershopper.pythonanywhere.com/moisturizer");
        moisturizersPage = new MoisturizersPage(driver);
    }

    @Given("I am on the cart page")
    public void cart_homepage() throws MalformedURLException {
        driver = initializeDriver(config.getBrowser());
        driver.get("https://weathershopper.pythonanywhere.com/cart");
        cartPage = new CartPage(driver);
    }

    @When("I check the temperature")
    public void i_check_the_temperature() {
        homePage.checkTemperature();
    }

    @And("I shop for moisturizers if the temperature is below 19")
    public void i_shop_for_moisturizers_if_the_temperature_is_below_19() {
        homePage.shopForMoisturizers();
    }

    @And("I shop for sunscreens if the temperature is above 34")
    public void i_shop_for_sunscreens_if_the_temperature_is_above_34() {
        homePage.shopForSunscreens();
    }

    @Then("I should be on the moisturizers page")
    public void i_should_be_on_the_moisturizers_page() {
        moisturizersPage = new MoisturizersPage(driver);
        moisturizersPage.verifyOnPage();
    }

    @Then("I should be on the sunscreens page")
    public void i_should_be_on_the_sunscreens_page() {
        sunscreensPage = new SunscreensPage(driver);
        sunscreensPage.verifyOnPage();
    }

    @When("I add the least expensive sunscreen with SPF 50 to the cart")
    public void i_add_the_least_expensive_sunscreen_with_spf_50_to_the_cart() {
        sunscreensPage.addCheapestSPF50();
    }

    @And("I add the least expensive sunscreen with SPF 30 to the cart")
    public void i_add_the_least_expensive_sunscreen_with_spf_30_to_the_cart() {
        sunscreensPage.addCheapestSPF30();
    }

    @Then("the cart should contain two sunscreens")
    public void the_cart_should_contain_two_sunscreens() {
        cartPage = new CartPage(driver);
        cartPage.verifyItemsInCart(2);
    }

    @When("I verify the items in the cart")
    public void i_verify_the_items_in_the_cart() {
        cartPage.verifyItemsInCart(2);
    }

    @And("I proceed to checkout with Stripe test card numbers")
    public void i_proceed_to_checkout_with_stripe_test_card_numbers() {
        cartPage.proceedToCheckout();
        checkoutPage = new CheckoutPage(driver);
        // Using config class to retrieve payment details from credentials.json
        checkoutPage.enterPaymentDetails(
                config.getEmail(),
                config.getCardNumber(),
                config.getExpiry(),
                config.getCVV(),
                config.getZipCode()
        );
    }

    @Then("the payment should be successful or fail by design")
    public void the_payment_should_be_successful_or_fail_by_design() {
        checkoutPage.verifyPaymentOutcome();
    }

    @When("I add the least expensive moisturizer containing Aloe to the cart")
    public void i_add_the_least_expensive_moisturizer_containing_aloe_to_the_cart() {
        moisturizersPage.addCheapestAloe();
    }

    @And("I add the least expensive moisturizer containing Almond to the cart")
    public void i_add_the_least_expensive_moisturizer_containing_almond_to_the_cart() {
        moisturizersPage.addCheapestAlmond();
    }

    @Then("the cart should contain two moisturizers")
    public void the_cart_should_contain_two_moisturizers() {
        cartPage = new CartPage(driver);
        cartPage.verifyItemsInCart(2);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
