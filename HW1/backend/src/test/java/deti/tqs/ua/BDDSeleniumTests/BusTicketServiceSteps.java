package deti.tqs.ua.BDDSeleniumTests;
import io.cucumber.java.After;
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

    private TripsListPage TripsListPage;

/*    @Given("the user entered in the website")
    public void the_user_entered_in_the_website() {
        driver = new FirefoxDriver();
        //maximize window
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        buyTicketPage = new BuyTicketPage(driver);
        listTripsPage = new ListTripsPage(driver);
        homePage.open();
    }*/
}
