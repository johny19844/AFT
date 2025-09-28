import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Assertions;

public class ya_testTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");
    }

    @Test
    public void testAddToCartAndCheckCart() {
        // Логин
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.sendKeys("standard_user");

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        // Добавление товара в корзину
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCartButton.click();

        // Проверка обновления счетчика корзины
        WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
        Assertions.assertTrue(cartBadge.isDisplayed());

        // Проверка изменения состояния кнопки товара
        WebElement removeButton = driver.findElement(By.id("remove-sauce-labs-backpack"));
        Assertions.assertFalse(removeButton.isDisplayed());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}