
package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {
    private WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterPaymentDetails(String email, String cardNumber, String expiry, String cvv, String zipCode) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Switch to the iframe containing the payment form, if applicable
        WebElement iframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iframe);

        // Wait for the email input field to be visible and interactable
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
        emailField.sendKeys(email);
        sleep(600);  // Wait for 500 milliseconds


        WebElement cardNumberField = wait.until(ExpectedConditions.elementToBeClickable(By.id("card_number")));
        for (char digit : cardNumber.toCharArray()) {
            cardNumberField.sendKeys(String.valueOf(digit));
            sleep(100);  // Slight pause between each digit
        }
        sleep(500);  // Wait for 500 milliseconds after entering the card number


        WebElement expiryField = wait.until(ExpectedConditions.elementToBeClickable(By.id("cc-exp")));
        for (char digit : expiry.toCharArray()) {
            expiryField.sendKeys(String.valueOf(digit));
            sleep(100);  // Slight pause between each digit
        }
        sleep(500);  // Wait for 500 milliseconds


        WebElement cvvField = wait.until(ExpectedConditions.elementToBeClickable(By.id("cc-csc")));
        cvvField.sendKeys(cvv);
        sleep(600);  // Wait for 500 milliseconds

        WebElement zipCodeField = wait.until(ExpectedConditions.elementToBeClickable(By.id("billing-zip")));
        zipCodeField.sendKeys(zipCode);
        sleep(500);  // Wait for 500 milliseconds

        // Wait for the submit button or equivalent to be clickable and then click it
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("iconTick")));
        submitButton.click();

        // Switch back to the default content if necessary
        driver.switchTo().defaultContent();
    }

    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void verifyPaymentOutcome() {
        // Define the expected URL for a successful payment
        String expectedUrl = "https://weathershopper.pythonanywhere.com/confirmation";
        // Get the current URL with a wait to ensure the page has loaded
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        // Get the current URL
        String currentUrl = driver.getCurrentUrl();

        // Verify if the current URL matches the expected URL
        if (!currentUrl.equals(expectedUrl)) {
            throw new AssertionError("Payment was not successful. Current URL: " + currentUrl);
        }
    }
}


