import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ya_testTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testYandexSearch() {
        driver.get("https://yandex.ru");

        WebDriverWait wait = new WebDriverWait(driver, 10);

        // Вводим текст в строку поиска
        driver.findElement(By.xpath("//input[@class='input-txt']")).sendKeys("сказки Пушкина");

        // Нажимаем кнопку Найти
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Проверяем релевантность результатов поиска
        String expectedResult = "сказки Пушкина";
        String result = driver.findElement(By.xpath("//h3")).getText();
        Assertions.assertEquals(expectedResult, result);

        // Логирование шагов
        System.out.println("Шаг 1: Переход на страницу Yandex");
        System.out.println("Шаг 2: Ввод текста в строку поиска");
        System.out.println("Шаг 3: Нажатие кнопки Найти");
        System.out.println("Шаг 4: Проверка релевантности результатов поиска");

    }

    @AfterEach
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}