package deti.tqs.ua.BDDSeleniumTests;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

@ExtendWith(SeleniumJupiter.class)
public class BusTicketServiceSteps {
    private WebDriver driver;
    private HomePage homePage;

    private TripsListPage tripsListPage;
    private ReservePage reservePage;
    private ConfirmationPage confirmationPage;

    @Given("the user landed on the Home page")
    public void the_user_landed_on_the_Home_page() {
        driver = new FirefoxDriver();
        //maximize window
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        tripsListPage = new TripsListPage(driver);
        reservePage = new ReservePage(driver);
        confirmationPage = new ConfirmationPage(driver);
        homePage.open();
    }

    @When("the user searches for trips, clicking on the Search button")
    public void the_user_searches_for_trips_clicking_on_the_Search_button() {
        homePage.clickSeeTripsButton();
    }

    @And("selects the first trip")
    public void selects_the_first_trip() {
        tripsListPage.clickFirstTrip();
    }
}
