package deti.tqs.ua.BDDSeleniumTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TripsListPage {
    private final WebDriver driver;

    public TripsListPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFirstTrip() {
        driver.findElement(By.cssSelector("css=.hover\\\\3A bg-secondary:nth-child(1) .btn")).click();
    }
}
