import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.seleniumhq.selenium.devtools.Connection;

public class ya_testTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/your/chromedriver");
        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @Test
    public void testLoginAndAddToCart() {
        driver.get("https://www.saucedemo.com");
        // Пример добавления логина и пароля через PageFactory
        PageFactory.initElements(driver, this);
        // Здесь добавьте код для логина
        // Пример добавления товара в корзину и проверки счетчика
        // Пример проверки изменения состояния кнопки товара
    }
}