import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MtsOnlinePaymentTest {
    private static final String SITE_URL = "https://www.mts.by/";
    private static final String EXPECTED_BLOCK_TITLE = "Онлайн пополнение без комиссии";
    private static final String TEST_PHONE_NUMBER = "297777777";

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get(SITE_URL);
        acceptCookiesIfPresent();
    }

    @Test
    //1. Проверка названия блока
    void verifyBlockTitle() {
        WebElement blockTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/h2")));

        assertAll(
                () -> assertTrue(blockTitle.isDisplayed(), "Блок не отображается"),
                () -> assertEquals(EXPECTED_BLOCK_TITLE, blockTitle.getText(),
                        "Название блока не соответствует ожидаемому")
        );
    }

    @Test
    //2. Проверка наличия логотипов платёжных систем
    void verifyPaymentSystemLogos() {
        List<WebElement> paymentLogos = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul")));

        String[] expectedLogos = {"Visa", "Verified by Visa", "MasterCard", "Белкарт", "МИР"};

        assertAll(
                () -> assertFalse(paymentLogos.isEmpty(), "Логотипы платежных систем не найдены"),
                () -> assertEquals(5, paymentLogos.size(), "Количество логотипов не соответствует ожидаемому")
        );

        for (String logo : expectedLogos) {
            assertTrue(isLogoPresent(logo), "Логотип " + logo + " не найден");
        }
    }


    @Test
    //3. Проверка работы ссылки «Подробнее о сервисе»
    void verifyDetailsLink() {
        WebElement detailsLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/a")));

        String initialUrl = driver.getCurrentUrl();
        detailsLink.click();

        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(initialUrl)));
        assertNotEquals(initialUrl, driver.getCurrentUrl(), "Ссылка не ведет на другую страницу");
    }

    @Test
    //4. Проверка формы для 'Услуги связи' и кнопки «Продолжить»
    void verifyServiceForm() {
        // Находим поле ввода телефона
        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"connection-phone\"]")));

        // Вводим тестовый номер
        phoneInput.clear();
        phoneInput.sendKeys(TEST_PHONE_NUMBER);

        // Проверяем, что номер введен корректно
        assertEquals(TEST_PHONE_NUMBER, phoneInput.getAttribute("value"),
                "Номер телефона введен некорректно");

        // Находим и кликаем кнопку "Продолжить"
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"pay-connection\"]/button")));
        continueButton.click();

        // Проверяем, что произошел переход или появилась следующая форма

            assertNotEquals(SITE_URL, driver.getCurrentUrl(),
                    "Не произошел переход после нажатия кнопки");

    }


    private boolean isLogoPresent(String logoName) {
        try {
            return driver.findElement(By.xpath(
                    String.format("//img[contains(@alt, '%s')]", logoName))).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private void acceptCookiesIfPresent() {
        try {
            WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Принять') or contains(text(), 'Согласен')]")));
            acceptButton.click();
        } catch (TimeoutException e) {
            // Кнопка не найдена, ничего не делаем
        }
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}