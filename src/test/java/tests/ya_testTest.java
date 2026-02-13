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
import org.openqa.selenium.support.ui.ExpectedConditions;
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

        public WwwSaucedemoComPage clickСтраница() {
            logger.info("Выполнение шага CLICK для элемента: страница");
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(страница));
            element.click();
            Assertions.assertTrue(страница.isDisplayed(), "Элемент страницы не отображается после клика");
            return this;
        }

    }
}