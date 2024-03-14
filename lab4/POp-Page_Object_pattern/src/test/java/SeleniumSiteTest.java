import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import webpages.ConfirmationPage;
import webpages.HomePage;
import webpages.PurchasePage;
import webpages.ReservePage;

@ExtendWith(SeleniumJupiter.class)
public class SeleniumSiteTest {
    WebDriver driver;

    @BeforeEach
    public void setup(){
        //use FF Driver
        driver = new FirefoxDriver();
    }

    @Test
    public void testFlightPurchase() {

        driver.get("https://blazedemo.com/");

        //Create object of HomePage Class
        HomePage home = new HomePage(driver);

        assertThat(home.getTitle()).isEqualTo("Welcome to the Simple Travel Agency!");

        home.toPortSelect("London");
        assertThat(home.getToPortSelect()).isEqualTo("Buenos Aires");

        //Create object of Reserve Class
        ReservePage reservePage = new ReservePage(driver);

        reservePage.chooseFlight(1);
        reservePage.fillPassengerDetails("Marcolino Castro", "Rua das Flores", "Aveiro", "Portugal", "3800-340", "123456789", "Marcs");

        //Create object of Purchase Class
        PurchasePage purchasePage = new PurchasePage(driver);
        assertThat(purchasePage.getTitle()).isEqualTo("Your flight from TLV to SFO has been reserved.");

        assertThat(purchasePage.getTotalPrice()).isEqualTo("914.76");

        // Não sei se seria desta forma, ou se poderia deixar as 2 coisas juntas como em cima
        purchasePage.setForm(
                "Marcolino",
                "Rua das Flores",
                "Aveiro",
                "Portugal",
                "3800-320",
                "Visa",
                "123456789",
                "11",
                "2017",
                "Marcs"
        );

        assertThat(purchasePage.getInputName()).isEqualTo("Marcolino");

        assertThat(purchasePage.getInputState()).isEqualTo("Portugal");

        assertThat(purchasePage.getInputCreditCardNumber()).isEqualTo("123456789");

        assertThat(purchasePage.getInputYear()).isEqualTo("2017");

        purchasePage.clickPurchaseFlightButton();

        //Create object of Confirmation Class
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);

        confirmationPage.confirmPurchase();
        assertEquals(confirmationPage.getConfirmationMessage(), "Thank you for your purchase today!");

    }

    @AfterEach
    public void close(){
        driver.close();
    }
}
