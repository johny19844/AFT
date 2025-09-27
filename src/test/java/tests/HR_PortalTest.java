import org.junit.jupiter.api.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Before;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.util.concurrent.TimeUnit;

public class HR_PortalTest {

    private static final Logger logger = LoggerFactory.getLogger(HR_PortalTest.class);
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://hr-portal.test.com");
    }

    @Test
    public void testCreateAndPublishJob() {
        logger.info("Step 1: Create job");
        driver.findElement(createJobLocator()).click();
        driver.findElement(departmentLocator()).sendKeys("IT");
        driver.findElement(salaryLocator()).sendKeys("150000");
        driver.findElement(descriptionLocator()).sendKeys("Requirements and responsibilities");
        driver.findElement(saveJobLocator()).click();
        Assertions.assertTrue(driver.findElement(jobStatusLocator()).getText().contains("Активна"));

        logger.info("Step 2: Publish job");
        driver.findElement(publishedJobLocator()).click();
        driver.findElement(platformsLocator()).click();
        driver.findElement(hhRuLocator()).click();
        driver.findElement(linkedinLocator()).click();
        Assertions.assertTrue(driver.findElement(publicationIDLocator()).getText().isPresent());
    }

    @Test
    public void testAddCandidate() {
        logger.info("Step 3: Add candidate");
        driver.findElement(candidatesSectionLocator()).click();
        driver.findElement(addCandidateLocator()).click();
        driver.findElement(fioLocator()).sendKeys("Ivanov Ivan Ivanovich");
        driver.findElement(emailLocator()).sendKeys("ivanov@email.com");
        driver.findElement(phoneLocator()).sendKeys("+79991234567");
        driver.findElement(attachResumeLocator()).click();
        driver.findElement(selectJobLocator()).click();
        driver.findElement(pythonDeveloperLocator()).click();
        Assertions.assertTrue(driver.findElement(candidateAddedLocator()).getText().contains("добавлен в базу данных"));
    }

    @Test
    public void testScheduleInterview() {
        logger.info("Step 4: Schedule interview");
        driver.findElement(candidateNameLocator()).click();
        driver.findElement(scheduleInterviewLocator()).click();
        driver.findElement(dateLocator()).sendKeys("25.12.2024");
        driver.findElement(timeLocator()).sendKeys("15:00");
        driver.findElement(interviewTypeLocator()).click();
        driver.findElement(technicalInterviewLocator()).click();
        driver.findElement(participantsLocator()).sendKeys("Технический лидер, HR-менеджер");
        Assertions.assertTrue(driver.findElement(invitationSentLocator()).getText().contains("Приглашение отправлено"));
    }

    @Test
    public void testFeedbackAndUpdateStatus() {
        logger.info("Step 5: Feedback and update status");
        driver.findElement(feedbackLocator()).sendKeys("Сильный middle-разработчик");
        driver.findElement(statusUpdateLocator()).click();
        Assertions.assertTrue(driver.findElement(statusUpdatedLocator()).getText().contains("Рекомендован к найму"));
    }

    @Test
    public void testCreateAndSendOffer() {
        logger.info("Step 6: Create and send offer");
        driver.findElement(createOfferLocator()).click();
        driver.findElement(salaryInOfferLocator()).sendKeys("160000");
        driver.findElement(startDateLocator()).sendKeys("15.01.2025");
        driver.findElement(conditionsLocator()).sendKeys("Испытательный срок 3 месяца");
        driver.findElement(sendOfferLocator()).click();
        Assertions.assertTrue(driver.findElement(offerSentLocator()).getText().contains("Оффер создан и отправлен"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    private By createJobLocator() { return By.id("create-job"); }
    private By departmentLocator() { return By.id("department"); }
    private By salaryLocator() { return By.id("salary"); }
    private By descriptionLocator() { return By.id("description"); }
    private By saveJobLocator() { return By.id("save-job"); }
    private By jobStatusLocator() { return By.id("job-status"); }
    // ... other locators defined similarly

    private By invitationsSentLocator() { return By.id("invitation-sent"); }
    private By feedbackLocator() { return By.id("feedback"); }
    private By statusUpdateLocator() { return By.id("status-update"); }
    private By statusUpdatedLocator() { return By.id("status-updated"); }
    private By createOfferLocator() { return By.id("create-offer"); }
    private By salaryInOfferLocator() { return By.id("salary-in-offer"); }
    private By startDateLocator() { return By.id("start-date"); }
    private By conditionsLocator() { return By.id("conditions"); }
    private By sendOfferLocator() { return By.id("send-offer"); }
    private By offerSentLocator() { return By.id("offer-sent"); }

    // Define WebDriverWait and other utilities as needed
}