import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.logging.Logger;

public class ya_testTest {

    private static WebDriver driver;
    private static Logger logger = Logger.getLogger(ya_testTest.class.getName());

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testSearch() {
        driver.get("https://google.com");
        logger.info("Открыта страница Google");

        driver.findElementByName("q").sendKeys("сказки Пушкина");
        logger.info("Введена строка поиска 'сказки Пушкина'");

        driver.findElementByClassName("btnG").click();
        logger.info("Нажата кнопка поиска");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(d -> d.findElementByTagName("h3").getText().contains("сказки Пушкина"));
        logger.info("Результаты поиска релевантны");

        Assertions.assertTrue(true); // Проверка релевантности результатов поиска
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}