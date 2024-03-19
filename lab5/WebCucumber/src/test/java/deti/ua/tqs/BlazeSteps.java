package deti.ua.tqs;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class BlazeSteps {

    private final WebDriver driver = new FirefoxDriver();

    @Given("I am on https:\\/\\/www.blazedemo.com\\/ home page")
    public void setup() {
        driver.get("https://www.blazedemo.com/");
    }

    @When("I write\\/select {string} on the {string} input")
    public void selectFromPort(String fromCity, String fromPort) {
        WebElement element = driver.findElement(By.name(fromPort));
        element.sendKeys(fromCity);
    }

    @When("I click {string}")
    public void click_flight_button(String button) {
        WebElement element = driver.findElement(By.xpath("//input[@type='submit' and @value='" + button + "']"));
        element.click();
    }

    @When("I click Choose This Flight on flight {int}")
    public void click_choose_flight(Integer flightN) {
        List<WebElement> rows = driver.findElements(By.xpath("//tr[.//input[@type='submit']]"));
        WebElement element = null;
        for (WebElement row : rows) {
            WebElement flightNumber = row.findElement(By.xpath(".//td[2]")); // assuming the flight number is in the second td
            if (Integer.parseInt(flightNumber.getText()) == flightN) {
                element = row.findElement(By.xpath(".//input[@type='submit']"));
                break;
            }
        }
        if (element == null) {
            throw new RuntimeException("Flight not found");
        }
        element.click();
    }

    @Then("I should be redirected to a page with the title {string}")
    public void check_page_title(String title) {
        if (!driver.getTitle().equals(title)) {
            throw new RuntimeException("Title is not as expected");
        }
    }


    @After()
    public void closeBrowser() {
        driver.quit();
    }



}
