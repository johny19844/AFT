import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ya_testTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");
    }

    @Test
    public void test() {
        // Вход
        driver.findElement(ExpectedConditions.visibilityOf(driver.findElement(ExpectedConditions.cssSelector("#user-name")))).sendKeys("standard_user");
        driver.findElement(ExpectedConditions.visibilityOf(driver.findElement(ExpectedConditions.cssSelector("#password")))).sendKeys("secret_sauce");
        driver.findElement(ExpectedConditions.visibilityOf(driver.findElement(ExpectedConditions.cssSelector("#loginButton")))).click();

        // Найти товар и добавить в корзину
        driver.findElement(ExpectedConditions.visibilityOf(driver.findElement(ExpectedConditions.cssSelector("#item_0")))).click();
        driver.findElement(ExpectedConditions.visibilityOf(driver.findElement(ExpectedConditions.cssSelector(".btn-cart")))).click();

        // Проверить обновление счетчика корзины
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(driver.findElement(ExpectedConditions.cssSelector(".cart-count"))));
        String cartCount = driver.findElement(ExpectedConditions.visibilityOf(driver.findElement(ExpectedConditions.cssSelector(".cart-count")))).getText();
        assert cartCount.equals("1");

        // Проверить изменение состояния кнопки товара
        boolean isInCart = driver.findElement(ExpectedConditions.visibilityOf(driver.findElement(ExpectedConditions.cssSelector("#item_0")))).getAttribute("class").contains("in-cart");
        assert isInCart;

        driver.quit();
    }
}