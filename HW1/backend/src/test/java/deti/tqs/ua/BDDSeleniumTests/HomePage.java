package deti.tqs.ua.BDDSeleniumTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private final WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(){
        driver.get("http://localhost:3000/");
    }

    public void clickSeeTripsButton() {
        driver.findElement(By.cssSelector(".btn")).click();
    }
}
