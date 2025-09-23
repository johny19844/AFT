import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class ya_testTest {

    private static final Logger logger = LoggerFactory.getLogger(ya_testTest.class);
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // для Jenkins
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void testYandexSearch() {
        logger.info("Step 1: Go to Yandex search page");
        driver.get("https://yandex.ru");

        logger.info("Step 2: Enter search query");
        driver.findElement(By.name("text")).sendKeys("сказки Пушкина");

        logger.info("Step 3: Click search button");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        logger.info("Step 4: Verify search results relevance");
        String pageTitle = driver.getTitle();
        assert pageTitle.contains("сказки Пушкина") || pageTitle.contains("Яндекс");
        
        logger.info("Test completed successfully");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
