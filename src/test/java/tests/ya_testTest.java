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
        driver.get("https://demoqa.com/text-box");
    }

    @Test
    public void testTextBox() {
        WebElement fullNameInput = driver.findElement(By.id("userName"));
        WebElement emailInput = driver.findElement(By.id("userEmail"));
        WebElement currentAddressInput = driver.findElement(By.id("currentAddress"));
        WebElement permanentAddressInput = driver.findElement(By.id("permanentAddress"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        fullNameInput.sendKeys("Иван Петроsавs");
        emailInput.sendKeys("ivan.petrov@example.com");
        currentAddressInput.sendKeys("Москва, ул. Примерная, д. 1");
        permanentAddressInput.sendKeys("Санкт-Петербург, ул. Тестовая, д. 2");
        submitButton.click();

        WebElement result = driver.findElement(By.id("output"));
        String name = result.findElement(By.id("name")).getText();
        String email = result.findElement(By.id("email")).getText();
        String currentAddress = result.findElement(By.id("currentAddress")).getText();
        String permanentAddress = result.findElement(By.id("permanentAddress")).getText();

        assertEquals("Иван Петроsавs", name);
        assertEquals("ivan.petrov@example.com", email);
        assertEquals("Москва, ул. Примерная, д. 1", currentAddress);
        assertEquals("Санкт-Петербург, ул. Тестовая, д. 2", permanentAddress);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}