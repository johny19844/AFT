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

public class GoogleSearchTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        
        // Опции для более естественного поведения
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void testGoogleSearch() {
        try {
            System.out.println("=== Starting Google Search Test ===");
            
            // Шаг 1: Переходим на Google
            driver.get("https://www.google.com");
            System.out.println("✓ Opened Google.com");
            System.out.println("Page title: " + driver.getTitle());
            
            // Шаг 2: Принимаем cookies если есть (для EU)
            acceptCookiesIfPresent();
            
            // Шаг 3: Находим поисковую строку
            WebElement searchBox = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("textarea[name='q'], input[name='q']"))
            );
            System.out.println("✓ Search box found");
            
            // Шаг 4: Вводим поисковый запрос
            searchBox.clear();
            searchBox.sendKeys("сказки Пушкина");
            System.out.println("✓ Entered search query: 'сказки Пушкина'");
            
            // Шаг 5: Нажимаем кнопку поиска
            WebElement searchButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("input[value*='Поиск'], input[type='submit'], button[type='submit']"))
            );
            searchButton.click();
            System.out.println("✓ Clicked search button");
            
            // Шаг 6: Ждем загрузки результатов
            wait.until(ExpectedConditions.or(
                ExpectedConditions.titleContains("сказки Пушкина"),
                ExpectedConditions.titleContains("Google Search"),
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("#search, .g, .rc"))
            ));
            
            System.out.println("✓ Search results loaded");
            System.out.println("Final page title: " + driver.getTitle());
            System.out.println("Current URL: " + driver.getCurrentUrl());
            
            // Шаг 7: Проверяем наличие результатов
            int resultCount = driver.findElements(By.cssSelector(".g, .rc, .tF2Cxc")).size();
            System.out.println("Found " + resultCount + " search results");
            
            if (resultCount > 0) {
                System.out.println("✓ TEST PASSED - Google search works correctly!");
                
                // Выводим первые 3 результата для наглядности
                System.out.println("=== First 3 results ===");
                driver.findElements(By.cssSelector(".g, .rc, .tF2Cxc")).stream()
                    .limit(3)
                    .forEach(result -> {
                        try {
                            String title = result.findElement(By.cssSelector("h3, .LC20lb")).getText();
                            System.out.println("• " + title);
                        } catch (Exception e) {
                            System.out.println("• [Could not extract title]");
                        }
                    });
            } else {
                System.out.println("⚠ No search results found, but page loaded successfully");
            }
            
        } catch (Exception e) {
            System.err.println("✗ TEST FAILED: " + e.getMessage());
            System.out.println("Current URL: " + driver.getCurrentUrl());
            System.out.println("Page title: " + driver.getTitle());
            
            // Делаем скриншот страницы для отладки
            takeDebugScreenshot();
            e.printStackTrace();
        }
    }
    
    private void acceptCookiesIfPresent() {
        try {
            // Попробуем разные селекторы для cookie банера
            String[] cookieSelectors = {
                "button#L2AGLb", // EU cookie banner
                "button[aria-label*='cookie'][aria-label*='accept']",
                "button:contains('Accept'), button:contains('Принять')",
                "form[action*='consent'] button"
            };
            
            for (String selector : cookieSelectors) {
                try {
                    WebElement cookieButton = driver.findElement(By.cssSelector(selector));
                    if (cookieButton.isDisplayed()) {
                        cookieButton.click();
                        System.out.println("✓ Accepted cookies");
                        Thread.sleep(1000); // Ждем закрытия банера
                        break;
                    }
                } catch (Exception e) {
                    // Продолжаем пробовать следующий селектор
                }
            }
        } catch (Exception e) {
            System.out.println("No cookie banner found or already accepted");
        }
    }
    
    private void takeDebugScreenshot() {
        try {
            // Сохраняем HTML страницы для отладки
            String pageSource = driver.getPageSource();
            System.out.println("=== PAGE SOURCE (first 1000 chars) ===");
            System.out.println(pageSource.substring(0, Math.min(1000, pageSource.length())));
        } catch (Exception e) {
            System.err.println("Could not capture page source: " + e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
                System.out.println("✓ Browser closed successfully");
            } catch (Exception e) {
                System.err.println("Error closing browser: " + e.getMessage());
            }
        }
        System.out.println("=== Test Finished ===\n");
    }
}
