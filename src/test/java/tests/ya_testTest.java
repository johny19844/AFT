import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Assertions;

import java.util.concurrent.TimeUnit;

public class ya_testTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://demoqa.com/text-box");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testTextBox() {
        // Заполнение формы
        WebElement fullNameInput = driver.findElement(By.id("userName"));
        fullNameInput.sendKeys("Иван Петроsjj1йв");

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
        WebElement resultName = driver.findElement(By.id("name"));
        WebElement resultEmail = driver.findElement(By.id("email"));
        WebElement resultCurrentAddress = driver.findElement(By.id("currentAddress"));
        WebElement resultPermanentAddress = driver.findElement(By.id("permanentAddress"));

        Assertions.assertEquals("Иван Петроsjj1йв", resultName.getText());
        Assertions.assertEquals("ivan.petrov@example.com", resultEmail.getText());
        Assertions.assertEquals("Москва, ул. Примерная, д. 1", resultCurrentAddress.getText());
        Assertions.assertEquals("Санкт-Петербург, ул. Тестовая, д. 2", resultPermanentAddress.getText());
    }
}