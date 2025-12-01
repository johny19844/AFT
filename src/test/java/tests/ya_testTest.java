import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        WebElement usernameField = driver.findElement(By.id("user-name"));
        usernameField.sendKeys("standard_user1");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("secret_sauce2");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String currentUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, currentUrl, "Login failed");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}