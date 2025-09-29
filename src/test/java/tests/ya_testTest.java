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
        String chromeDriverPath = System.getProperty("webdriver.chrome.driver");
        driver = new ChromeDriver(chromeDriverPath);
        driver.get("https://www.saucedemo.com");
    }

    @Test
    public void testLogin() {
        // Реализация теста на логин
        driver.findElement(ExpectedConditions.id("user-name")).sendKeys("standard_user");
        driver.findElement(ExpectedConditions.id("password")).sendKeys("secret_sauce");
        driver.findElement(ExpectedConditions.id("login-button")).click();
    }

    @Test
    public void testAddToCart() {
        // Реализация теста на добавление товара в корзину
        driver.findElement(ExpectedConditions.cssSelector(".inventory_item")).click();
        driver.findElement(ExpectedConditions.cssSelector(".add_to_cart")).click();
    }

    @Test
    public void testCartCounterUpdate() {
        // Реализация теста на проверку обновления счетчика корзины
        int initialCartCount = Integer.parseInt(driver.findElement(ExpectedConditions.cssSelector(".cart-count")).getText());
        testAddToCart();
        int updatedCartCount = Integer.parseInt(driver.findElement(ExpectedConditions.cssSelector(".cart-count")).getText());
        assert (updatedCartCount > initialCartCount);
    }

    @Test
    public void testButtonStateChange() {
        // Реализация теста на проверку изменения состояния кнопки товара
        boolean initialButtonState = driver.findElement(ExpectedConditions.cssSelector(".inventory_item")).getAttribute("class").contains("selected");
        testAddToCart();
        boolean updatedButtonState = driver.findElement(ExpectedConditions.cssSelector(".inventory_item")).getAttribute("class").contains("selected");
        assert (updatedButtonState);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}