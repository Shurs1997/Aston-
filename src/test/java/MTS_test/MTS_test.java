package MTS_test;

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
    private static final String SITE_URL = "https://www.mts.by";
    private static final String EXPECTED_BLOCK_TITLE = "Онлайн пополнение\nбез комиссии";
    private static final String TEST_PHONE_NUMBER = "(29)777-77-77";

    private static final By BLOCK_TITLE = By.xpath("//*[@class=\"pay__wrapper\"]/h2");
    private static final By PAYMENT_LOGOS = By.xpath("//*[@class=\"pay__partners\"]/ul/li");
    private static final By DETAILS_LINK = By.xpath("//*[@class=\"pay__wrapper\"]/a");
    private static final By PHONE_INPUT = By.xpath("//*[@id=\"connection-phone\"]");
    private static final By CONTINUE_BUTTON = By.xpath("//*[@id=\"pay-connection\"]/button");
    private static final By AMOUNT_FIELD = By.xpath("//*[@id=\"connection-sum\"]");
    private static final By COOKIE_BUTTON = By.xpath("//button[contains(text(), 'Принять') or contains(text(), 'Согласен')]");

    WebDriver driver;
    WebDriverWait wait;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get(SITE_URL);
        acceptCookiesIfPresent();
    }

    @Test
    @DisplayName("1. Проверка названия блока")
    void verifyBlockTitle() {
        WebElement blockTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(BLOCK_TITLE));
        assertAll(
                () -> assertTrue(blockTitle.isDisplayed(), "Блок не отображается"),
                () -> assertEquals(EXPECTED_BLOCK_TITLE, blockTitle.getText(), "Название блока не соответствует ожидаемому")
        );
    }

    @Test
    @DisplayName("2. Проверка логотипов платёжных систем")
    void verifyPaymentSystemLogos() {
        List<WebElement> logos = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PAYMENT_LOGOS));
        String[] expectedLogos = {"Visa", "Verified By Visa", "MasterCard", "MasterCard Secure Code", "Белкарт"};

        assertAll(
                () -> assertFalse(logos.isEmpty(), "Логотипы не найдены"),
                () -> assertEquals(expectedLogos.length, logos.size(), "Количество логотипов отличается")
        );

        for (String logo : expectedLogos) {
            assertTrue(isLogoPresent(logo), "Логотип " + logo + " не найден");
        }
    }

    @Test
    @DisplayName("3. Проверка перехода по ссылке «Подробнее о сервисе»")
    void verifyDetailsLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(DETAILS_LINK));
        String initialUrl = driver.getCurrentUrl();
        link.click();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(initialUrl)));

        assertNotEquals(initialUrl, driver.getCurrentUrl(), "URL не изменился после перехода");
    }

    @Test
    @DisplayName("4. Проверка формы и кнопки «Продолжить»")
    void verifyServiceForm() {
        WebElement phone = wait.until(ExpectedConditions.visibilityOfElementLocated(PHONE_INPUT));
        phone.clear();
        phone.sendKeys(TEST_PHONE_NUMBER);
        assertEquals(TEST_PHONE_NUMBER, phone.getAttribute("value"), "Номер телефона введен некорректно");

        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(CONTINUE_BUTTON));
        continueBtn.click();

        try {
            WebElement amountField = wait.until(ExpectedConditions.visibilityOfElementLocated(AMOUNT_FIELD));
            assertTrue(amountField.isDisplayed(), "Поле 'Сумма' не отображается");
        } catch (TimeoutException e) {
            assertNotEquals(SITE_URL, driver.getCurrentUrl(), "Переход не произошел");
        }
    }

    private boolean isLogoPresent(String logoName) {
        List<WebElement> logos = driver.findElements(By.xpath(String.format("//img[contains(@alt, '%s')]", logoName)));
        return !logos.isEmpty() && logos.get(0).isDisplayed();
    }

    private void acceptCookiesIfPresent() {
        try {
            WebElement acceptBtn = wait.until(ExpectedConditions.elementToBeClickable(COOKIE_BUTTON));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", acceptBtn); // Более надёжный клик
        } catch (TimeoutException | NoSuchElementException ignored) {}
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }

}
