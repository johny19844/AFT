import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ya_testTest {

    private WebDriver driver;

    @Test
    public void test() {
        this.driver = initializeDriver();

        driver.get("https://www.saucedemo.com");

        // Login
        driver.findElement(LoginPage.LOGIN_INPUT).sendKeys("standard_user");
        driver.findElement(LoginPage.PASSWORD_INPUT).sendKeys("secret_sauce");
        driver.findElement(LoginPage.LOGIN_BUTTON).click();

        // Find product and add to cart
        driver.findElement(ProductPage.PRODUCT_NAME_INPUT).click();
        driver.findElement(ProductPage.ADD_TO_CART_BUTTON).click();

        // Check cart counter update
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(CartPage.CART_COUNTER));
        int cartCounter = Integer.parseInt(driver.findElement(CartPage.CART_COUNTER).getText());
        assert cartCounter == 1;

        // Check product status change
        boolean productAvailable = driver.findElement(ProductPage.PRODUCT_NAME_INPUT).getAttribute("disabled").equals("true");
        assert !productAvailable;

        driver.quit();
    }

    private WebDriver initializeDriver() {
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver");
        return new ChromeDriver();
    }
}