package pages;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MoisturizersPage {
    private WebDriver driver;

    public MoisturizersPage(WebDriver driver) {
        this.driver = driver;
    }
    private int extractPrice(WebElement productElement) {
        String priceText = productElement.findElement(By.xpath(".//p[contains(text(),'Price:')]")).getText();
        return Integer.parseInt(priceText.replaceAll("[^0-9]", "").trim());
    }

    // Finds and clicks the cheapest SPF-50 product
    public void addCheapestSPF50() {
        // Wait until the products are visible on the page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".text-center.col-4")));

        // Find the cheapest SPF-50 product
        WebElement cheapestProduct = products.stream()
                .filter(product -> product.getText().contains("SPF-50"))
                .min((p1, p2) -> {
                    int price1 = extractPrice(p1);
                    int price2 = extractPrice(p2);
                    return Integer.compare(price1, price2);
                })
                .orElseThrow(() -> new RuntimeException("No SPF-50 products found"));

        // Click the 'Add' button for the cheapest SPF-50 product
        cheapestProduct.findElement(By.xpath(".//button[text()='Add']")).click();
    }
    public void addCheapestSPF30() {
        // Wait for the product elements to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".text-center.col-4")));

        // Find the cheapest product with "SPF-30"
        WebElement cheapestProduct = products.stream()
                .filter(product -> product.getText().contains("SPF-30"))
                .min((p1, p2) -> {
                    // Extract the price from the product description
                    int price1 = extractPrice(p1);
                    int price2 = extractPrice(p2);
                    return Integer.compare(price1, price2);
                })
                .orElseThrow(() -> new RuntimeException("No SPF-30 products found"));

        // Click the "Add" button for the cheapest product found
        cheapestProduct.findElement(By.xpath(".//button[contains(text(),'Add')]")).click();
    }

    public void addCheapestAloe() {
        // Wait until the products are visible on the page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".text-center.col-4")));

        // Find the cheapest SPF-50 product
        WebElement cheapestProduct = products.stream()
                .filter(product -> product.getText().contains("Aloe"))
                .min((p1, p2) -> {
                    int price1 = extractPrice(p1);
                    int price2 = extractPrice(p2);
                    return Integer.compare(price1, price2);
                })
                .orElseThrow(() -> new RuntimeException("No Aloe products found"));

        // Click the 'Add' button for the cheapest SPF-50 product
        cheapestProduct.findElement(By.xpath(".//button[text()='Add']")).click();

    }

    public void addCheapestAlmond() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".text-center.col-4")));


        WebElement cheapestProduct = products.stream()
                .filter(product -> product.getText().contains("Almond"))
                .min((p1, p2) -> {
                    int price1 = extractPrice(p1);
                    int price2 = extractPrice(p2);
                    return Integer.compare(price1, price2);
                })
                .orElseThrow(() -> new RuntimeException("No Almond products found"));


        cheapestProduct.findElement(By.xpath(".//button[text()='Add']")).click();

    }


    public void verifyOnPage() {

        String currentUrl = driver.getCurrentUrl();
        assertTrue("Expected to be on the Moisturizers page, but was not.",currentUrl.contains("moisturizer"));


        String pageTitle = driver.getTitle();
        assertTrue("Expected to see Moisturizers in the page title, but was not.",pageTitle.contains("Moisturizers"));


        String pageHeader = driver.findElement(By.cssSelector("h2")).getText();
        assertEquals("Moisturizers", pageHeader);
    }

}