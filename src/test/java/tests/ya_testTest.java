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
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        fullNameInput.sendKeys("Иван Петро1в");
        emailInput.sendKeys("ivan.petrov@example.com");
        currentAddressInput.sendKeys("Москва, ул. Примерная, д. 1");
        permanentAddressInput.sendKeys("Санкт-Петербург, ул. Тестовая, д. 2");
        submitButton.click();

        WebElement resultName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        WebElement resultEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        WebElement resultCurrentAddress = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("currentAddress")));
        WebElement resultPermanentAddress = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("permanentAddress")));

        assertEquals("Иван Петро1в", resultName.getText());
        assertEquals("ivan.petrov@example.com", resultEmail.getText());
        assertEquals("Москва, ул. Примерная, д. 1", resultCurrentAddress.getText());
        assertEquals("Санкт-Петербург, ул. Тестовая, д. 2", resultPermanentAddress.getText());
    }
}