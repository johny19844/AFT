import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class ya_testTest {
    private WebDriver driver;

    @Test
    public void testLoginAndAddToCart() {
        System.setProperty("webdriver.chrome.driver", "path/to/chrome/driver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com");

        // Введите логин и пароль
        driver.findElement(ExpectedConditions.cssSelector(".user-name")).sendKeys("standard_user");
        driver.findElement(ExpectedConditions.cssSelector(".password")).sendKeys("secret_sauce");
        driver.findElement(ExpectedConditions.cssSelector(".btn-submit")).click();

        // Найти товар и добавить в корзину
        driver.findElement(ExpectedConditions.cssSelector(".inventory_item_name")).click();
        driver.findElement(ExpectedConditions.cssSelector(".btn-add-to-cart")).click();

        // Проверить обновление счетчика корзины
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(ExpectedConditions.cssSelector(".cart-quantity")), "1"));

        // Проверить изменение состояния кнопки товара
        boolean buttonEnabled = driver.findElement(ExpectedConditions.cssSelector(".btn-add-to-cart")).isEnabled();
        assert !buttonEnabled;

        driver.quit();
    }
}