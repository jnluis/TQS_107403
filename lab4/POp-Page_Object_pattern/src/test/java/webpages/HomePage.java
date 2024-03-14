package webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;

    //Page URL
    private static String PAGE_URL="https://blazedemo.com/";

    //Locators

    //Apply as Developer Button
    @FindBy(xpath = "/html/body/div[2]/div/h1")
    private WebElement title;

    @FindBy(xpath = "/html/body/div[3]/form/div/input")
    private WebElement findFlightsButton;
    @FindBy(name = "toPort")
    private WebElement toPortSelect;

    //Constructor
    public HomePage(WebDriver driver){
        this.driver=driver;
        driver.get(PAGE_URL);
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return title.getText();
    }

    public String getToPortSelect() {
        return toPortSelect.getText();
    }

    public void toPortSelect(String toPort) {
        toPortSelect.sendKeys(toPort);
    }

    public void clickFindFlightsButton() {
        findFlightsButton.click();
    }
}
