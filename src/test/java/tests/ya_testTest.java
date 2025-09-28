import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ya_testTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void testShoppingCart() {
        driver.get("https://www.saucedemo.com");

        // Логин
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameInput.sendKeys("standard_user");
        passwordInput.sendKeys("secret_sauce");
        loginButton.click();

        // Найти товар и добавить в корзину
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCartButton.click();

        // Проверить обновление счетчика корзины
        WebElement shoppingCartBadge = driver.findElement(By.className("shopping_cart_badge"));
        wait.until(ExpectedConditions.textToBePresentInElement(shoppingCartBadge, "1"));

        // Проверить изменение состояния кнопки товара
        WebElement removeButton = driver.findElement(By.id("remove-sauce-labs-backpack"));
        wait.until(ExpectedConditions.visibilityOf(removeButton));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}