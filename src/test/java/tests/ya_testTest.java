package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ya_testTest {
    private WebDriver driver;
    private WwwSaucedemoComPage page;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.page = new WwwSaucedemoComPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }

    @Test
    public void testya_test() {
        page.clickСтраница();
    }

    public static class WwwSaucedemoComPage {
        private WebDriver driver;
        private WebDriverWait wait;
        private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(WwwSaucedemoComPage.class);

        @FindBy(id = "user-name")
        private WebElement страница;

        public WwwSaucedemoComPage(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            PageFactory.initElements(driver, this);
        }

        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.support.ui.ExpectedConditions;
        import org.openqa.selenium.support.ui.WebDriverWait;
        import java.time.Duration;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;

        public class LoginPage {
            private WebDriver driver;
            private WebDriverWait wait;
            private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

            public LoginPage(WebDriver driver) {
                this.driver = driver;
                this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            }

            public LoginPage clickСтраница() {
                driver.get("https://www.saucedemo.com");
                logger.info("Открыта страница https://www.saucedemo.com");

                WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
                usernameField.clear();
                usernameField.sendKeys("standard_user1");
                logger.info("Заполнено поле пользователь значением standard_user1");

                WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
                passwordField.clear();
                passwordField.sendKeys("secret_sauce1");
                logger.info("Заполнено поле пароль значением secret_sauce1");

                WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button")));
                loginButton.click();
                logger.info("Нажата кнопка вход");

                WebElement productLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("product_label")));
                if (productLabel.isDisplayed()) {
                    logger.info("Вход выполнен успешно");
                } else {
                    logger.info("Вход не выполнен");
                }

                return this;
            }
        }

    }
}