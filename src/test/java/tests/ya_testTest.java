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

    @Test
    public void testTextBoxForm() {
        WebElement fullNameInput = driver.findElement(By.id("userName"));
        WebElement emailInput = driver.findElement(By.id("userEmail"));
        WebElement currentAddressInput = driver.findElement(By.id("currentAddress"));
        WebElement permanentAddressInput = driver.findElement(By.id("permanentAddress"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        fullNameInput.sendKeys("Иван Петроsdfавs");
        emailInput.sendKeys("ivan.petrov@example.com");
        currentAddressInput.sendKeys("Москва, ул. Примерная, д. 1");
        permanentAddressInput.sendKeys("Санкт-Петербург, ул. Тестовая, д. 2");
        submitButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("output")));

        WebElement outputDiv = driver.findElement(By.id("output"));
        List<WebElement> outputElements = outputDiv.findElements(By.tagName("p"));

        assertEquals("Name:" + "Иван Петроsdfавs", outputElements.get(0).getText());
        assertEquals("Email:" + "ivan.petrov@example.com", outputElements.get(1).getText());
        assertEquals("Current Address :" + "Москва, ул. Примерная, д. 1", outputElements.get(2).getText());
        assertEquals("Permanent Address :" + "Санкт-Петербург, ул. Тестовая, д. 2", outputElements.get(3).getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}