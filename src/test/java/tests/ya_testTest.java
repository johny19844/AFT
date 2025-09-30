import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ya_testTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://demoqa.com/text-box");
    }

    @Test
    public void testTextBoxForm() {
        WebElement fullNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Full Name']")));
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='email'][placeholder='name@example.com']")));
        WebElement currentAddressInput = driver.findElement(By.cssSelector("textarea#currentAddress"));
        WebElement permanentAddressInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("textarea[placeholder='Permanent Address']")));
        WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[type='button'][class='btn btn-primary']")));

        fullNameInput.sendKeys("Иван Петров");
        emailInput.sendKeys("ivan.petrov@example.com");
        currentAddressInput.sendKeys("Москва, ул. Примерная, д. 1");
        permanentAddressInput.sendKeys("Санкт-Петербург, ул. Тестовая, д. 2");
        submitButton.click();

        WebElement resultFullName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        WebElement resultEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        WebElement resultCurrentAddress = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("currentAddress")));
        WebElement resultPermanentAddress = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("permanentAddress")));

        assertEquals("Иван Петров", resultFullName.getText());
        assertEquals("ivan.petrov@example.com", resultEmail.getText());
        assertEquals("Москва, ул. Примерная, д. 1", resultCurrentAddress.getText());
        assertEquals("Санкт-Петербург, ул. Тестовая, д. 2", resultPermanentAddress.getText());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}