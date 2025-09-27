import org.junit.jupiter.api.After;
import org.junit.jupiter.api.Before;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class hr_miniTest {

    private static final Logger logger = LoggerFactory.getLogger(hr_miniTest.class);
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testFireEmployee() {
        logger.info("Test start");
        driver.get("https://hr.com");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(d -> d.findElement(org.openqa.selenium.By.id("fireEmployeeBtn")).isDisplayed());
        driver.findElement(org.openqa.selenium.By.id("fireEmployeeBtn")).click();
        wait.until(d -> d.findElement(org.openqa.selenium.By.id("successMsg")).isDisplayed());
        logger.info("Successfully fired employee");
    }

    @After
    public void tearDown() {
        logger.info("Closing browser");
        driver.quit();
    }
}