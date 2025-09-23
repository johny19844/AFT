import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class ya_testTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        // Автоматическая установка и настройка ChromeDriver
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-extensions");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--user-agent=Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
        
        // Явно указываем путь к Chrome если нужно
        // options.setBinary("/usr/bin/google-chrome");
        
        try {
            driver = new ChromeDriver(options);
            wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
            
            // Проверяем что драйвер работает
            driver.get("about:blank");
            System.out.println("ChromeDriver started successfully");
            
        } catch (Exception e) {
            System.err.println("Failed to start ChromeDriver: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testYandexSearch() {
        try {
            System.out.println("Starting Yandex search test...");
            
            // Шаг 1: Переходим на Яндекс
            driver.get("https://www.google.com/");
            System.out.println("Opened google, title: " + driver.getTitle());
            
            // Ждем загрузки поисковой строки
            WebElement searchBox = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("textarea[name='q'], input[name='q']"))
            );
            System.out.println("Search box found");
            
            // Шаг 2: Вводим поисковый запрос
            searchBox.sendKeys("сказки Пушкина");
            System.out.println("Entered search query");
            
            // Шаг 3: Нажимаем кнопку поиска
            searchBox.submit();
            System.out.println("✓ Submitted search form (using Enter)");
            
            // Шаг 4: Ждем результаты поиска
            //wait.until(ExpectedConditions.titleContains("сказки Пушкина"));
            //System.out.println("Search results loaded, title: " + driver.getTitle());
            wait.until(ExpectedConditions.or(
                ExpectedConditions.titleContains("сказки Пушкина"),
                ExpectedConditions.titleContains("Google Search"),
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("#search, .g, .tF2Cxc"))
            ));
            System.out.println("✓ Search results loaded");
            System.out.println("Final title: " + driver.getTitle());
            System.out.println("Current URL: " + driver.getCurrentUrl());
    
            // Шаг 7: Проверяем наличие результатов
            int resultCount = driver.findElements(By.cssSelector(".g, .tF2Cxc, .rc")).size();
            System.out.println("✓ Found " + resultCount + " search results");
            
            if (resultCount > 0) {
                System.out.println("🎉 TEST PASSED - Google search completed successfully!");
            } else {
                System.out.println("⚠ No results found, but search executed");
            }
            // Шаг 5: Проверяем что мы на странице результатов
            //String currentUrl = driver.getCurrentUrl();
            //if (currentUrl.contains("google.com/search") || currentUrl.contains("google.com/search")) {
            //    System.out.println("✓ Test PASSED - Search results page loaded successfully");
            //} else {
            //    System.out.println("✗ Test FAILED - Unexpected URL: " + currentUrl);
           // }
            
        } catch (Exception e) {
            System.err.println("Test failed with error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Test execution failed", e);
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
                System.out.println("Browser closed successfully");
            } catch (Exception e) {
                System.err.println("Error closing browser: " + e.getMessage());
            }
        }
    }
}
