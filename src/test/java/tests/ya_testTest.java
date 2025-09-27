import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.*;

public class ya_testTest {
    private static WebDriver driver;
    private static Logger logger;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver");
        driver = new ChromeDriver();
        logger = LoggerFactory.getLogger(ya_testTest.class);
        driver.manage().window().maximize();
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void testGoogleSearch() {
        driver.get("https://google.com");
        logger.info("Открыта страница Google");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(d -> d.findElementById("lst-ib")).sendKeys("сказки Пушкина");
        logger.info("Введена фраза 'сказки Пушкина'");

        wait.until(d -> d.findElementByClassName("btnG")).click();
        logger.info("Нажата кнопка поиска");

        wait.until(d -> assertEquals("Результаты поиска по фразе 'сказки Пушкина'", "div#main", d.findElementByClassName("srg")));
        logger.info("Проверена релевантность результатов поиска");

        driver.close();
        logger.info("Браузер закрыт");
    }
}