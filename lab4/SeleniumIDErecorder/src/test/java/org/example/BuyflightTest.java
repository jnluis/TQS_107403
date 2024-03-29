/*
// Generated by Selenium IDE, with Junit4
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class BuyflightTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new FirefoxDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void buyflight() {
    driver.get("https://www.blazedemo.com/");
    driver.manage().window().setSize(new Dimension(968, 1037));
    {
      WebElement dropdown = driver.findElement(By.name("fromPort"));
      dropdown.findElement(By.xpath("//option[. = 'Philadelphia']")).click();
    }
    {
      List<WebElement> elements = driver.findElements(By.name("fromPort"));
      assert(elements.size() > 0);
    }
    driver.findElement(By.cssSelector(".form-inline:nth-child(1) > option:nth-child(2)")).click();
    driver.findElement(By.name("toPort")).click();
    {
      WebElement dropdown = driver.findElement(By.name("toPort"));
      dropdown.findElement(By.xpath("//option[. = 'London']")).click();
    }
    driver.findElement(By.cssSelector(".form-inline:nth-child(4) > option:nth-child(3)")).click();
    driver.findElement(By.cssSelector(".btn-primary")).click();
    driver.findElement(By.cssSelector("tr:nth-child(1) .btn")).click();
    driver.findElement(By.id("inputName")).click();
    driver.findElement(By.id("inputName")).sendKeys("Marcolino");
    {
      List<WebElement> elements = driver.findElements(By.id("inputName"));
      assert(elements.size() > 0);
    }
    driver.findElement(By.id("address")).click();
    driver.findElement(By.id("address")).sendKeys("Rua");
    {
      List<WebElement> elements = driver.findElements(By.id("address"));
      assert(elements.size() > 0);
    }
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
    {
      List<WebElement> elements = driver.findElements(By.cssSelector("h1"));
      assert(elements.size() > 0);
    }
  }
}
*/
