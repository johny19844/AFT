import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.Container;
import org.testcontainers.containers.DockerImageName;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.ContainerizedTestContainer;
import org.testcontainers.junit.jupiter.DriverContainer;

public class ya_testTest {
    private static WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger(ya_testTest.class);

    @BeforeEach
    public void setUp() {
        Container container = new GenericContainer(DockerImageName.parse("selenium/standalone-chrome:4.5.0-jaunty"));
        container.start();
        driver = new ChromeDriver(container.getNetwork(), container.getDefaultExposedPorts().get(0));
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void testSearchOnYandex() {
        driver.get("https://yandex.ru");
        logger.info("Открыта страница Яндекса");

        driver.findElement(By.name("text")).sendKeys("сказки Пушкина");
        logger.info("Введено значение \"сказки Пушкина\" в строку поиска");

        driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
        logger.info("Нажата кнопка поиска");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(d -> d.findElement(By.cssSelector(".serp-list"));
        logger.info("Результаты поиска загружены");

        // Проверка релевантности результатов поиска
        // Реализация проверки релевантности зависит от конкретных критериев

        driver.close();
    }
}