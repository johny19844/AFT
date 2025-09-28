package ya_test;

import org.junit.jupiter.api.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Before;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ya_testTest {

    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private CartPage cartPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testScenario() {
        // 1. Открыть страницу https://www.saucedemo.com
        driver.get("https://www.saucedemo.com");

        // 2. Выполнить авторизацию
        loginPage.login("standard_user", "secret_sauce");

        // 3. Найти товар и добавить в корзину
        Product product = homePage.findProduct("backpack");
        product.addToCart();

        // 4. Проверить обновление счетчика корзины
        Assertions.assertEquals(1, cartPage.getCartCount());

        // 5. Проверить изменение состояния кнопки товара
        product = cartPage.getProductInCart();
        Assertions.assertTrue(product.isInCart());
    }
}

class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public Product findProduct(String productName) {
        WebElement element = driver.findElement(ExpectedConditions.visibilityOfElementLocated(productNameSelector(productName)));
        return new Product(element, driver);
    }

    // Other methods...
}

class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String password) {
        // Implementation...
    }
}

class CartPage {
    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getCartCount() {
        // Implementation...
    }

    public Product getProductInCart() {
        // Implementation...
    }
}

class Product {
    private WebElement element;
    private WebDriver driver;

    public Product(WebElement element, WebDriver driver) {
        this.element = element;
        this.driver = driver;
    }

    public void addToCart() {
        // Implementation...
    }

    public boolean isInCart() {
        // Implementation...
    }
}