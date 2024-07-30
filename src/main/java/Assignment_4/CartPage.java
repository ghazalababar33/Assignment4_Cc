package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
//import java.util.List;

public class CartPage {
    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyItemsInCart(int expectedItemCount) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cartElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cart")));
        String cartText = cartElement.getText();
        int itemCount = extractItemCount(cartText);

        if (itemCount != expectedItemCount) {
            throw new AssertionError("Expected " + expectedItemCount + " items in the cart, but found " + itemCount);
        }
    }
    private int extractItemCount(String cartText) {

        String[] parts = cartText.split(" ");
        return Integer.parseInt(parts[parts.length - 2]);
    }

    public void proceedToCheckout() {
        driver.findElement(By.id("cart")).click();
        driver.findElement(By.className("stripe-button-el")).click();
    }
}