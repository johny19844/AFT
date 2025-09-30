import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ya_testTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testTextBox() {
        driver.get("https://demoqa.com/text-box");

        WebElement fullNameInput = driver.findElement(By.id("userName"));
        WebElement emailInput = driver.findElement(By.id("userEmail"));
        WebElement currentAddressInput = driver.findElement(By.id("currentAddress"));
        WebElement permanentAddressInput = driver.findElement(By.id("permanentAddress"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        fullNameInput.sendKeys("Иван Петроsв");
        emailInput.sendKeys("ivan.petrov@example.com");
        currentAddressInput.sendKeys("Москва, ул. Примерная, д. 1");
        permanentAddressInput.sendKeys("Санкт-Петербург, ул. Тестовая, д. 2");
        submitButton.click();

        WebElement resultFullName = driver.findElement(By.id("name"));
        WebElement resultEmail = driver.findElement(By.id("email"));
        WebElement resultCurrentAddress = driver.findElement(By.id("currentAddress"));
        WebElement resultPermanentAddress = driver.findElement(By.id("permanentAddress"));

        assertEquals("Иван Петроsв", resultFullName.getText());
        assertEquals("ivan.petrov@example.com", resultEmail.getText());
        assertEquals("Москва, ул. Примерная, д. 1", resultCurrentAddress.getText());
        assertEquals("Санкт-Петербург, ул. Тестовая, д. 2", resultPermanentAddress.getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}