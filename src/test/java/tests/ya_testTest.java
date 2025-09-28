import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
        driver.get("https://www.saucedemo.com");
    }

    @Test
    public void testAddToCartAndCheckCart() {
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce");
        loginButton.click();

        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCartButton.click();

        WebElement shoppingCartBadge = driver.findElement(By.className("shopping_cart_badge"));
        Assertions.assertTrue(shoppingCartBadge.isDisplayed(), "Shopping cart badge is not displayed");

        WebElement cartItem = driver.findElement(By.className("cart_item"));
        Assertions.assertTrue(cartItem.isDisplayed(), "Cart item is not displayed");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}