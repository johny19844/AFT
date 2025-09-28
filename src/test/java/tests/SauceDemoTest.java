import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SauceDemoTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "c:\\Users\\apors\\AI\\AFT_Agent\\launch_test\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // @Test
    // public void testSuccessfulLogin() {
    //     // Открываем сайт
    //     driver.get("https://www.saucedemo.com");
        
    //     // Проверяем, что находимся на странице логина
    //     assertTrue(driver.getCurrentUrl().contains("saucedemo.com"));
    //     assertTrue(driver.findElement(By.className("login_logo")).isDisplayed());
        
    //     // Вводим корректные учетные данные
    //     login("standard_user", "secret_sauce");
        
    //     // Проверяем успешный вход
    //     wait.until(ExpectedConditions.urlContains("inventory.html"));
    //     assertTrue(driver.findElement(By.className("inventory_list")).isDisplayed());
    //     assertEquals("Products", driver.findElement(By.className("title")).getText());
    // }

    @Test
    public void testAddToCart() {
        // Логинимся
        driver.get("https://www.saucedemo.com");
        login("standard_user", "secret_sauce");
        
        // Добавляем товар в корзину
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCartButton.click();
        
        // Проверяем, что кнопка изменилась на "Remove"
        WebElement removeButton = wait.until(ExpectedConditions
            .visibilityOfElementLocated(By.id("remove-sauce-labs-backpack")));
        assertTrue(removeButton.isDisplayed());
        
        // Проверяем счетчик корзины
        WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
        assertEquals("1", cartBadge.getText());
    }

    // @Test
    // public void testCompletePurchase() {
    //     // Логинимся
    //     driver.get("https://www.saucedemo.com");
    //     login("standard_user", "secret_sauce");
        
    //     // Добавляем товары в корзину
    //     driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
    //     driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        
    //     // Переходим в корзину
    //     driver.findElement(By.className("shopping_cart_link")).click();
        
    //     // Проверяем корзину
    //     wait.until(ExpectedConditions.urlContains("cart.html"));
    //     assertEquals("Your Cart", driver.findElement(By.className("title")).getText());
        
    //     List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
    //     assertEquals(2, cartItems.size());
        
    //     // Нажимаем Checkout
    //     driver.findElement(By.id("checkout")).click();
        
    //     // Заполняем информацию о покупателе
    //     wait.until(ExpectedConditions.urlContains("checkout-step-one.html"));
    //     fillCheckoutInfo("Иван", "Иванов", "123456");
        
    //     // Продолжаем
    //     driver.findElement(By.id("continue")).click();
        
    //     // Проверяем overview
    //     wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));
    //     assertEquals("Checkout: Overview", driver.findElement(By.className("title")).getText());
        
    //     // Завершаем покупку
    //     driver.findElement(By.id("finish")).click();
        
    //     // Проверяем завершение
    //     wait.until(ExpectedConditions.urlContains("checkout-complete.html"));
    //     assertEquals("Checkout: Complete!", driver.findElement(By.className("title")).getText());
    //     assertTrue(driver.findElement(By.className("complete-header")).isDisplayed());
    //     assertEquals("Thank you for your order!", 
    //         driver.findElement(By.className("complete-header")).getText());
    // }

    // @Test
    // public void testSortingProducts() {
    //     // Логинимся
    //     driver.get("https://www.saucedemo.com");
    //     login("standard_user", "secret_sauce");
        
    //     // Получаем список товаров до сортировки
    //     List<WebElement> itemsBeforeSort = driver.findElements(By.className("inventory_item_name"));
    //     String firstItemBefore = itemsBeforeSort.get(0).getText();
        
    //     // Сортируем по имени (Z to A)
    //     WebElement sortDropdown = driver.findElement(By.className("product_sort_container"));
    //     Select select = new Select(sortDropdown);
    //     select.selectByValue("za");
        
    //     // Ждем обновления списка
    //     wait.until(ExpectedConditions.not(ExpectedConditions
    //         .textToBePresentInElementLocated(
    //             By.xpath("//div[@class='inventory_item_name'][1]"), 
    //             firstItemBefore
    //         )));
        
    //     // Проверяем сортировку
    //     List<WebElement> itemsAfterSort = driver.findElements(By.className("inventory_item_name"));
    //     String firstItemAfter = itemsAfterSort.get(0).getText();
        
    //     assertNotEquals(firstItemBefore, firstItemAfter);
    // }

    // @Test
    // public void testRemoveFromCart() {
    //     // Логинимся
    //     driver.get("https://www.saucedemo.com");
    //     login("standard_user", "secret_sauce");
        
    //     // Добавляем товар
    //     driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        
    //     // Проверяем, что товар добавлен
    //     WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
    //     assertEquals("1", cartBadge.getText());
        
    //     // Удаляем товар
    //     driver.findElement(By.id("remove-sauce-labs-backpack")).click();
        
    //     // Проверяем, что счетчик корзины исчез
    //     wait.until(ExpectedConditions.invisibilityOfElementLocated(
    //         By.className("shopping_cart_badge")));
        
    //     // Проверяем, что кнопка снова "Add to cart"
    //     WebElement addButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
    //     assertTrue(addButton.isDisplayed());
    //     assertEquals("Add to cart", addButton.getText());
    // }

    // @Test
    // public void testLogout() {
    //     // Логинимся
    //     driver.get("https://www.saucedemo.com");
    //     login("standard_user", "secret_sauce");
        
    //     // Открываем боковое меню
    //     driver.findElement(By.id("react-burger-menu-btn")).click();
        
    //     // Нажимаем Logout
    //     WebElement logoutLink = wait.until(ExpectedConditions
    //         .elementToBeClickable(By.id("logout_sidebar_link")));
    //     logoutLink.click();
        
    //     // Проверяем, что вышли в систему
    //     wait.until(ExpectedConditions.urlContains("saucedemo.com"));
    //     assertTrue(driver.findElement(By.id("login-button")).isDisplayed());
    // }

    // @Test
    // public void testInvalidLogin() {
    //     driver.get("https://www.saucedemo.com");
        
    //     // Пытаемся войти с неверными данными
    //     login("invalid_user", "wrong_password");
        
    //     // Проверяем сообщение об ошибке
    //     WebElement errorMessage = wait.until(ExpectedConditions
    //         .visibilityOfElementLocated(By.xpath("//h3[@data-test='error']")));
        
    //     assertTrue(errorMessage.isDisplayed());
    //     assertTrue(errorMessage.getText().contains("Username and password do not match"));
    // }

    private void login(String username, String password) {
        WebElement usernameField = wait.until(ExpectedConditions
            .elementToBeClickable(By.id("user-name")));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));
        
        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();
    }

    private void fillCheckoutInfo(String firstName, String lastName, String zipCode) {
        WebElement firstNameField = wait.until(ExpectedConditions
            .elementToBeClickable(By.id("first-name")));
        WebElement lastNameField = driver.findElement(By.id("last-name"));
        WebElement zipCodeField = driver.findElement(By.id("postal-code"));
        
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        zipCodeField.sendKeys(zipCode);
    }

    // @Test
    // public void testMultipleUsers() {
    //     // Тестируем разных пользователей
    //     String[][] users = {
    //         {"standard_user", "secret_sauce"},
    //         {"problem_user", "secret_sauce"},
    //         {"performance_glitch_user", "secret_sauce"}
    //     };
        
    //     for (String[] user : users) {
    //         driver.get("https://www.saucedemo.com");
    //         login(user[0], user[1]);
            
    //         // Проверяем успешный вход
    //         wait.until(ExpectedConditions.urlContains("inventory.html"));
    //         assertTrue(driver.findElement(By.className("inventory_list")).isDisplayed());
            
    //         // Логаутимся для следующего теста
    //         driver.findElement(By.id("react-burger-menu-btn")).click();
    //         WebElement logoutLink = wait.until(ExpectedConditions
    //             .elementToBeClickable(By.id("logout_sidebar_link")));
    //         logoutLink.click();
    //     }
    // }
}
