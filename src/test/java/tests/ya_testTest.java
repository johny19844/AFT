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
    private static Logger logger;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        logger = Logger.getLogger(ya_testTest.class.getName());
        driver.manage().window().maximize();
    }

    @AfterAll
    public static void cleanup() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @Test
    public void testGoogleSearch() {
        logger.info("Тест Google Search начат");
        driver.get("https://yandex.ru");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(d -> d.findElement(org.openqa.selenium.By.id("sbox-I")));
        driver.findElement(org.openqa.selenium.By.id("sbox-I")).sendKeys("сказки Пушкина");
        driver.findElement(org.openqa.selenium.By.id("sbox-I")).submit();

        wait.until(d -> d.findElement(org.openqa.selenium.By.xpath("//h3[contains(.,'сказки Пушкина')]")));

        Assertions.assertTrue(driver.findElements(org.openqa.selenium.By.xpath("//h3[contains(.,'сказки Пушкина')]")).size() > 0, "Не найдены результаты поиска 'сказки Пушкина'");

        driver.close();
        logger.info("Тест Google Search завершён");
    }
}
