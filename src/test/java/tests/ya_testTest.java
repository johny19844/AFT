import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;
import org.testcontainers.containers.Container;
import org.testcontainers.containers.ChromeContainer;
import org.testcontainers.junit.jupiter.ContainerExtension;
import org.testcontainers.junit.jupiter.ContainerRuntime;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
public class ya_testTest {

    private static final Logger logger = LoggerFactory.getLogger(ya_testTest.class);

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeContainer container = new ChromeContainer();
        container.start();
        driver = container.getWebDriver();
        driver.get("https://yandex.ru");
    }

    @Test
    public void testYandexSearch() {
        logger.info("Step 1: Go to Yandex search page");
        driver.get("https://yandex.ru");

        logger.info("Step 2: Enter search query");
        driver.findElement(By.name("text")).sendKeys("сказки Пушкина");

        logger.info("Step 3: Click search button");
        driver.findElement(By.cssSelector(".search-button")).click();

        logger.info("Step 4: Verify search results relevance");
        String expectedResult = "сказки Пушкина";
        String actualResult = driver.findElement(By.cssSelector(".b-found")).getText();
        Assert.assertEquals(expectedResult, actualResult);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}