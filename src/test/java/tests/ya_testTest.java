import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ya_testTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://demoqa.com/text-box");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testTextBoxForm() {
        WebElement fullNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userName")));
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userEmail")));
        WebElement currentAddressInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("currentAddress")));
        WebElement permanentAddressInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("permanentAddress")));
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));

        fullNameInput.sendKeys("Иван Петроsfавs");
        emailInput.sendKeys("ivan.petrov@example.com");
        currentAddressInput.sendKeys("Москва, ул. Примерная, д. 1");
        permanentAddressInput.sendKeys("Санкт-Петербург, ул. Тестовая, д. 2");
        submitButton.click();

        WebElement outputFullName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        WebElement outputEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        WebElement outputCurrentAddress = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("currentAddress")));
        WebElement outputPermanentAddress = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("permanentAddress")));

        assertEquals("Иван Петроsfавs", outputFullName.getText());
        assertEquals("ivan.petrov@example.com", outputEmail.getText());
        assertEquals("Москва, ул. Примерная, д. 1", outputCurrentAddress.getText());
        assertEquals("Санкт-Петербург, ул. Тестовая, д. 2", outputPermanentAddress.getText());
    }
}