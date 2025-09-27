import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.logging.Logger;

public class Ya_testTest {
    private static WebDriver driver;
    private static final Logger logger = Logger.getLogger(Ya_testTest.class.getName());

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path_to_your_chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterAll
    public static void cleanup() {
        driver.quit();
    }

    @Test
    public void search_for_Pushkin_stories() {
        logger.info("Start test");
        driver.get("https://google.com");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(driver::findElement(By.name("q")));
        driver.findElement(By.name("q")).sendKeys("сказки Пушкина");
        driver.findElement(By.name("btnK")).click();
        wait.until(driver::findElement(By.xpath("//*[contains(text(), 'сказки Пушкина')]")));
        logger.info("Relevant search results found");
        driver.close();
        logger.info("Test completed");
    }
}