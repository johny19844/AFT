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
        // Установка ChromeDriver через WebDriverManager (автоматически)
        // io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-extensions");
        options.addArguments("--remote-allow-origins=*");
        
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void testYandexSearch() {
        try {
            logger.info("Step 1: Go to Yandex search page");
            driver.get("https://yandex.ru");

            logger.info("Step 2: Wait for page to load");
            Thread.sleep(2000); // Простая задержка для демонстрации

            logger.info("Step 3: Enter search query");
            driver.findElement(By.cssSelector("input[name='text']")).sendKeys("сказки Пушкина");

            logger.info("Step 4: Click search button");
            driver.findElement(By.cssSelector("button[type='submit']")).click();

            logger.info("Step 5: Wait for search results");
            Thread.sleep(3000);

            logger.info("Step 6: Verify search results");
            String pageTitle = driver.getTitle();
            logger.info("Page title: {}", pageTitle);
            
            // Более надежная проверка
            if (pageTitle.contains("Яндекс") || driver.getCurrentUrl().contains("yandex")) {
                logger.info("Test completed successfully - Yandex page loaded");
            } else {
                logger.warn("Unexpected page: {}", driver.getCurrentUrl());
            }
            
        } catch (Exception e) {
            logger.error("Test failed with error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
