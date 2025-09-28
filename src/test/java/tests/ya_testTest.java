import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.jupiter.api.Assertions;

public class ya_testTest {

    @Test
    public void testAddToCartAndCheckout() {
        // Устанавливаем путь к драйверу Chrome
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Настройка опций для ChromeDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Запуск в headless режиме

        // Инициализация WebDriver
        WebDriver driver = new ChromeDriver(options);

        try {
            // Открытие страницы
            driver.get("https://www.saucedemo.com");

            // Выполнение логина
            WebElement usernameField = driver.findElement(By.id("user-name"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("login-button"));

            usernameField.sendKeys("standard_user");
            passwordField.sendKeys("secret_sauce");
            loginButton.click();

            // Поиск и добавление товара в корзину
            WebElement addToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
            addToCartButton.click();

            // Проверка обновления счетчика корзины
            WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
            Assertions.assertEquals("1", cartBadge.getText());

            // Проверка изменения состояния кнопки товара
            addToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
            Assertions.assertTrue(addToCartButton.isDisplayed());
            Assertions.assertFalse(addToCartButton.isEnabled());

        } finally {
            // Закрытие браузера
            driver.quit();
        }
    }
}