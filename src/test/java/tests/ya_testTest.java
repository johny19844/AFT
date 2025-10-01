import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ya_testTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://demoqa.com/text-box");
    }

    @Test
    public void testTextBoxForm() {
        // Заполнение формы
        WebElement fullNameInput = driver.findElement(By.id("userName"));
        fullNameInput.sendKeys("Иван Петроsвs");

        WebElement emailInput = driver.findElement(By.id("userEmail"));
        emailInput.sendKeys("ivan.petrov@example.com");

        WebElement currentAddressInput = driver.findElement(By.id("currentAddress"));
        currentAddressInput.sendKeys("Москва, ул. Примерная, д. 1");

        WebElement permanentAddressInput = driver.findElement(By.id("permanentAddress"));
        permanentAddressInput.sendKeys("Санкт-Петербург, ул. Тестовая, д. 2");

        // Нажатие кнопки Submit
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        // Проверка отображения данных в блоке результата
        WebElement resultFullName = driver.findElement(By.id("name"));
        assertEquals("Иван Петроsвs", resultFullName.getText(), "Full Name does not match");

        WebElement resultEmail = driver.findElement(By.id("email"));
        assertEquals("ivan.petrov@example.com", resultEmail.getText(), "Email does not match");

        WebElement resultCurrentAddress = driver.findElement(By.id("currentAddress"));
        assertEquals("Москва, ул. Примерная, д. 1", resultCurrentAddress.getText(), "Current Address does not match");

        WebElement resultPermanentAddress = driver.findElement(By.id("permanentAddress"));
        assertEquals("Санкт-Петербург, ул. Тестовая, д. 2", resultPermanentAddress.getText(), "Permanent Address does not match");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}