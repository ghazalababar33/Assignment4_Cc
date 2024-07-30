package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class HomePage {
    private WebDriver driver;

    private By temperatureLocator = By.id("temperature");



    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public int getTemperature() {
        String temperatureText = driver.findElement(temperatureLocator).getText();
        return Integer.parseInt(temperatureText.split(" ")[0]);
    }

    public void checkTemperature() {
        int temperature = getTemperature();
        System.out.println("Current temperature: " + temperature);
    }

    public void shopForMoisturizers() {
        if (getTemperature() < 19) {
            driver.findElement(By.linkText("Buy moisturizers")).click();
        }
    }

    public void shopForSunscreens() {
        if (getTemperature() > 34) {
            driver.findElement(By.linkText("Buy sunscreens")).click();
        }
    }
}