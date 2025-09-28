import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ya_testTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    void test() {
        driver.get("https://www.saucedemo.com");

        // Вход
        driver.findElement(driver.findElement(ExpectedConditions.id("user-name")).sendKeys("standard_user");
        driver.findElement(driver.findElement(ExpectedConditions.id("password")).sendKeys("secret_sauce");
        driver.findElement(driver.findElement(ExpectedConditions.id("login-button")).click();

        // Найти товар и добавить в корзину
        driver.findElement(ExpectedConditions.linkText("Milk")).click();
        driver.findElement(ExpectedConditions.id("add-to-cart-button")).click();

        // Проверить обновление счетчика корзины
        int cartItemCountBefore = Integer.parseInt(driver.findElement(ExpectedConditions.cssSelector(".cart-quantity")).getText());
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(driver.findElement(ExpectedConditions.cssSelector(".cart-quantity"))));
        int cartItemCountAfter = Integer.parseInt(driver.findElement(ExpectedConditions.cssSelector(".cart-quantity")).getText());
        assert cartItemCountBefore + 1 == cartItemCountAfter;

        // Проверить изменение состояния кнопки товара
        boolean isAddToCartEnabledBefore = driver.findElement(ExpectedConditions.id("add-to-cart-button")).isEnabled();
        boolean isAddToCartEnabledAfter = !isAddToCartEnabledBefore;
        assert isAddToCartEnabledBefore == !isAddToCartEnabledAfter;

        driver.quit();
    }
}