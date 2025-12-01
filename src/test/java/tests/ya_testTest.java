import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ya_testTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com");
    }

    @Test
    public void testLogin() {
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("standard_user2");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("secret_sauce2");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        assertTrue(driver.getCurrentUrl().contains("inventory.html"), "Login failed");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}