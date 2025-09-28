import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class SauceDemoTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "c:\\Users\\apors\\AI\\AFT_Agent\\launch_test\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    void testAddToCart() {
        driver.get("https://www.saucedemo.com");
        login("standard_user", "secret_sauce");

        click(By.id("add-to-cart-sauce-labs-backpack"));

        assertTrue(waitForVisible(By.id("remove-sauce-labs-backpack")).isDisplayed());
        assertEquals("1", getText(By.className("shopping_cart_badge")));
    }

    private void login(String username, String password) {
        type(By.id("user-name"), username);
        type(By.id("password"), password);
        click(By.id("login-button"));
    }

    // Утилиты для упрощения кода
    private void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    private void type(By locator, String text) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        el.clear();
        el.sendKeys(text);
    }

    private WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }
}
