import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ya_testTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.get("https://demoqa.com/text-box");
    }

    @Test
    public void testTextBox() {
        // Заполнение формы
        WebElement fullNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userName")));
        fullNameInput.sendKeys("Иван Петроs1йв");

        WebElement emailInput = driver.findElement(By.id("userEmail"));
        emailInput.sendKeys("ivan.petrov@example.com");

        WebElement currentAddressInput = driver.findElement(By.id("currentAddress"));
        currentAddressInput.sendKeys("Москва, ул. Примерная, д. 1");

        WebElement permanentAddressInput = driver.findElement(By.id("permanentAddress"));
        permanentAddressInput.sendKeys("Санкт-Петербург, ул. Тестовая, д. 2");

        // Нажатие кнопки Submit
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        // Проверка отображения данных в результате
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("output")));
        List<WebElement> resultItems = result.findElements(By.tagName("p"));

        assertEquals("Name:Иван Петроs1йв", resultItems.get(0).getText());
        assertEquals("Email:ivan.petrov@example.com", resultItems.get(1).getText());
        assertEquals("Current Address :Москва, ул. Примерная, д. 1", resultItems.get(2).getText());
        assertEquals("Permanent Address :Санкт-Петербург, ул. Тестовая, д. 2", resultItems.get(3).getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}