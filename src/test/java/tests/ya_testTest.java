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
import org.springframework.util.Assert;
import org.testcontainers.containers.ChromeContainer;
import org.testcontainers.containers.Container;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;

@SpringBootTest
public class ya_testTest {
    private static WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger(ya_testTest.class);

    @BeforeEach
    public void setUp() {
        ChromeContainer chromeContainer = new ChromeContainer(DockerImageName.parse("chromeopenjdk:latest"));
        driver = chromeContainer.getWebDriver();
        driver.get("https://yandex.ru");
    }

    @Test
    public void searchAndVerifyResults() {
        driver.findElement(By.name("text")).sendKeys("нейронные сети в медицине");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> d.findElement(By.cssSelector(".search__results__header")).isDisplayed());

        Assert.notNull(driver.findElement(By.cssSelector(".search__results__header")), "Header not found");
        logger.info("Search results found");

        driver.quit();
    }

    @AfterEach
    public void cleanup() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}