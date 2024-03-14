package org.example;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import java.util.List;

@ExtendWith(SeleniumJupiter.class)
class BuyFlightTestJunit5 {
    private WebDriver driver;
    JavascriptExecutor js;

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        js = (JavascriptExecutor) driver;
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void buyflight() {
        driver.get("https://www.blazedemo.com/");
        driver.manage().window().setSize(new Dimension(968, 1037));
        WebElement dropdown = driver.findElement(By.name("fromPort"));
        dropdown.findElement(By.xpath("//option[. = 'Philadelphia']")).click();
        List<WebElement> elements = driver.findElements(By.name("fromPort"));
        assertTrue(elements.size() > 0);
        driver.findElement(By.cssSelector(".form-inline:nth-child(1) > option:nth-child(2)")).click();
        driver.findElement(By.name("toPort")).click();
        dropdown = driver.findElement(By.name("toPort"));
        dropdown.findElement(By.xpath("//option[. = 'London']")).click();
        driver.findElement(By.cssSelector(".form-inline:nth-child(4) > option:nth-child(3)")).click();
        driver.findElement(By.cssSelector(".btn-primary")).click();
        driver.findElement(By.cssSelector("tr:nth-child(1) .btn")).click();
        driver.findElement(By.id("inputName")).click();
        driver.findElement(By.id("inputName")).sendKeys("Marcolino");
        elements = driver.findElements(By.id("inputName"));
        assertTrue(elements.size() > 0);
        driver.findElement(By.id("address")).click();
        driver.findElement(By.id("address")).sendKeys("Rua");
        elements = driver.findElements(By.id("address"));
        assertTrue(elements.size() > 0);
        driver.findElement(By.id("city")).click();
        driver.findElement(By.id("city")).sendKeys("Aveiro");
        driver.findElement(By.cssSelector(".control-group:nth-child(6) > .controls")).click();
        driver.findElement(By.id("state")).click();
        driver.findElement(By.id("state")).sendKeys("Portugal");
        driver.findElement(By.id("zipCode")).click();
        driver.findElement(By.id("zipCode")).sendKeys("3800-380");
        driver.findElement(By.id("rememberMe")).click();
        assertTrue(driver.findElement(By.id("rememberMe")).isSelected());
        driver.findElement(By.cssSelector(".btn-primary")).click();
        elements = driver.findElements(By.cssSelector("h1"));
        assertTrue(elements.size() > 0);
    }
}
