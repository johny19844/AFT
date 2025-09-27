import org.junit.jupiter.api.After;
import org.junit.jupiter.api.Before;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ya_testTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void testSearch() {
        driver.get("https://google.com");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(drv -> drv.findElement(By.name("q")));

        String searchText = "сказки Пушкина";
        driver.findElement(By.name("q")).sendKeys(searchText);
        driver.findElement(By.name("q")).submit();

        // Проверка релевантности результатов поиска
        String expectedResult = "сказки Пушкина";
        boolean isResultRelevant = driver.findElement(By.cssSelector(".r a")).getText().contains(expectedResult);
        if (!isResultRelevant) {
            System.out.println("Результаты поиска нерелевантны");
        }

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}