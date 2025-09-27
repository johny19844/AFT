import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ya_testTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path_to_your_chromedriver");
        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testGoogleSearch() {
        driver.get("https://google.com");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        // Поиск
        String searchQuery = "сказки Пушкина";
        driver.findElement(By.name("q")).sendKeys(searchQuery);
        driver.findElement(By.name("q")).submit();

        wait.until(d -> d.findElement(By.cssSelector(".r a")));

        // Проверка результатов поиска
        String expectedResult = "сказки Пушкина";
        String actualResult = driver.findElement(By.cssSelector(".r a")).getText();
        Assertions.assertEquals(expectedResult, actualResult);

        // Закрытие браузера
        driver.quit();
    }
}