import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ya_testTest {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        // Устанавливаем путь к chrome driver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\apors\\AI\\AFT_Agent\\launch_test\\chromedriver.exe");
        // Создаем экземпляр WebDriver
        driver = new ChromeDriver();
        // Инициализируем время ожидания
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    void testAutomation() {
        // Открываем страницу
        driver.get("https://www.saucedemo.com");

        // Выполняем вход
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();

        // Находим товар и добавляем в корзину
        driver.findElement(By.cssSelector(".btn.btn-primary.inventory-item-addToCart-btn")).click();

        // Проверяем обновление счетчика корзины
        int cartCountBeforeAdd = driver.findElements(By.cssSelector(".shopping_cart.cart-icon")).size();
        driver.findElement(By.cssSelector(".btn.btn-primary.inventory-item-addToCart-btn")).click();
        int cartCountAfterAdd = driver.findElements(By.cssSelector(".shopping_cart.cart-icon")).size();
        assert (cartCountAfterAdd > cartCountBeforeAdd);

        // Проверяем изменение состояния кнопки товара
        String buttonTextBeforeAdd = driver.findElement(By.cssSelector(".inventory-item-addToCart-btn")).getText();
        driver.findElement(By.cssSelector(".btn.btn-primary.inventory-item-addToCart-btn")).click();
        String buttonTextAfterAdd = driver.findElement(By.cssSelector(".inventory-item-addToCart-btn")).getText();
        assert (!buttonTextBeforeAdd.equals(buttonTextAfterAdd));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}