import org.junit.jupiter.api.After;
import org.junit.jupiter.api.Before;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.SeleniumStandaloneContainer;
import org.testcontainers.utility.DockerImageName;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HR_PortalTest {

    private static final Logger logger = LoggerFactory.getLogger(HR_PortalTest.class);

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testFullCandidateSelectionCycle() {
        driver.get("http://example.com"); // Заменить на актуальный URL портала

        // Шаг 1: Создание вакансии
        logger.info("Шаг 1: Создание вакансии");
        driver.findElement(driver.findElement(driver.findElements(driver.findElements(driver.findElements(driver.findElements(driver.findElements(
                "//*[contains(text(), 'Вакансии')]").click());
        driver.findElement(driver.findElements(driver.findElements(driver.findElements(driver.findElements(
                "//*[contains(text(), 'Создать вакансию')]")).click());
        driver.findElement(driver.findElement(driver.findElements(driver.findElements(
                "//*[contains(text(), 'Должность:')]"))).sendKeys("Python Developer");
        driver.findElement(driver.findElement(driver.findElements(driver.findElements(
                "//*[contains(text(), 'Отдел:')]"))).sendKeys("IT");
        driver.findElement(driver.findElement(driver.findElements(driver.findElements(
                "//*[contains(text(), 'Заработная плата:')]"))).sendKeys("150000 руб.");
        driver.findElement(driver.findElements(driver.findElements(
                "//*[contains(text(), 'Описание:')]")).sendKeys("Требования и обязанности");
        driver.findElement(driver.findElements(
                "//*[contains(text(), 'Сохранить')]")).click();

        assertTrue(WebDriverWait.until(driver, (ExpectedCondition<Boolean>) driver1 -> {
            return driver1.findElement(driver1.findElements(driver1.findElements(driver.findElements(
                    "//*[contains(text(), 'Активна')]"))).isDisplayed();
        }, 10, TimeUnit.SECONDS));

        // Шаг 2: Публикация вакансии
        logger.info("Шаг 2: Публикация вакансии");
        driver.findElement(driver.findElements(
                "//*[contains(text(), 'Python Developer')]")).click();
        driver.findElement(driver.findElements(
                "//*[contains(text(), 'Опубликовать')]")).click();
        driver.findElement(driver.findElements(
                "//*[contains(text(), 'HH.ru')]")).click();
        driver.findElement(driver.findElements(
                "//*[contains(text(), 'LinkedIn')]")).click();

        assertTrue(WebDriverWait.until(driver, (ExpectedCondition<Boolean>) driver1 -> {
            return driver1.findElement(driver1.findElements(driver1.findElements(driver.findElements(
                    "//*[contains(text(), 'ID публикации')]"))).isDisplayed();
        }, 10, TimeUnit.SECONDS));

        // Шаг 3: Добавление кандидата
        logger.info("Шаг 3: Добавление кандидата");
        driver.findElement(driver.findElements(
                "//*[contains(text(), 'Кандидаты')]")).click();
        driver.findElement(driver.findElements(
                "//*[contains(text(), 'Добавить кандидата')]")).click();
        driver.findElement(driver.findElements(
                "//*[contains(text(), 'Иванов Иван Иванович')]")).sendKeys("Ivanov Ivan Ivanovich");
        driver.findElement(driver.findElements(
                "//*[contains(text(), 'Email:')]")).sendKeys("ivanov@email.com");
        driver.findElement(driver.findElements(
                "//*[contains(text(), 'Телефон:')]")).sendKeys("+79991234567");
        driver.findElement(driver.findElements(
                "//*[contains(text(), 'Загрузить резюме')]")).click();
        // Добавить код для загрузки резюме
        driver.findElement(driver.findElements(
                "//*[contains(text(), 'Python Developer')]")).click();

        assertTrue(WebDriverWait.until(driver, (ExpectedCondition<Boolean>) driver1 -> {
            return driver1.findElement(driver1.findElements(driver1.findElements(driver.findElements(
                    "//*[contains(text(), 'Кандидат добавлен')]"))).isDisplayed();
        }, 10, TimeUnit.SECONDS));

        // Шаг 4: Назначение собеседования
        logger.info("Шаг 4: Назначение собеседования");
        driver.findElement(driver.findElements(
                "//*[contains(text(), 'Иванов И.И.')]")).click();
        driver.findElement(driver.findElements(
                "//*[contains(text(), 'Назначить собеседование')]")).click();
        driver.findElement(driver.findElements(
                "//*[contains(text(), '25.12.2024')]")).sendKeys("25.12.2024");
        driver.findElement(driver.findElements(
                "//*[contains(text(), '15:00')]")).sendKeys("15:00");
        driver.findElement(driver.findElements(
                "//*[contains(text(), 'Техническое собеседование')]")).click();
        driver.findElement(driver.findElements(
                "//*[contains(text(), 'Технический лидер')]")).click();
        driver.findElement(driver.findElements(
                "//*[contains(text(), 'HR-менеджер')]")).click();

        assertTrue(WebDriverWait.until(driver, (ExpectedCondition<Boolean>) driver1 -> {
            return driver1.findElement(driver1.findElements(driver1.findElements(driver.findElements(
                    "//*[contains(text(), 'Приглашение отправлено')]"))).isDisplayed();
        }, 10, TimeUnit.SECONDS));

        // Шаг 5: Внесение результатов собеседования
        logger.info("Шаг 5: Внесение результатов собеседования");
        // Добавить код для заполнения фидбэка и изменения статуса кандидата

        assertTrue(WebDriverWait.until(driver, (ExpectedCondition<Boolean>) driver1 -> {
            return driver1.findElement(driver1.findElements(driver1.findElements(driver.findElements(
                    "//*[contains(text(), 'Рекомендован к найму')]"))).isDisplayed();
        }, 10, TimeUnit.SECONDS));

        // Шаг 6: Создание и отправка оффера
        logger.info("Шаг 6: Создание и отправка оффера");
        driver.findElement(driver.findElements(
                "//*[contains(text(), 'Создать оффер')]")).click();
        driver.findElement(driver.findElements(
                "//*[contains(text(), '160000 руб.')]")).sendKeys("160000 руб.");
        driver.findElement(driver.findElements(
                "//*[contains(text(), '15.01.2025')]")).sendKeys("15.01.2025");
        driver.findElement(driver.findElements(
                "//*[contains(text(), 'Испытательный срок 3 месяца')]")).click();
        driver.findElement(driver.findElements(
                "//*[contains(text(), 'Отправить')]")).click();

        assertTrue(WebDriverWait.until(driver, (ExpectedCondition<Boolean>) driver1 -> {
            return driver1.findElement(driver1.findElements(driver1.findElements(driver.findElements(
                    "//*[contains(text(), 'Оффер отправлен')]"))).isDisplayed();
        }, 10, TimeUnit.SECONDS));
    }
}