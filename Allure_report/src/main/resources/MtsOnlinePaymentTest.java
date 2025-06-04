package mts;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MtsHomePage;

import static org.junit.jupiter.api.Assertions.*;

@Epic("MTS")
@Feature("Онлайн пополнение без комиссии")
@DisplayName("Автотесты блока 'Онлайн пополнение'")
class MtsOnlinePaymentTest {

    private static final String SITE_URL = "https://www.mts.by/";
    private static final String EXPECTED_BLOCK_TITLE = "Онлайн пополнение\nбез комиссии";
    private static final String TEST_PHONE_NUMBER = "(29)777-77-77";
    private static final String[] EXPECTED_LOGOS = {"Visa", "Verified By Visa", "MasterCard", "MasterCard Secure Code", "Белкарт"};

    private WebDriver driver;
    private MtsHomePage homePage;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        homePage = new MtsHomePage(driver);
        homePage.open(SITE_URL);
    }

    @Test
    @Story("Визуальная проверка заголовка")
    @Description("Проверяет, что блок 'Онлайн пополнение без комиссии' отображается с правильным заголовком.")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("1. Проверка названия блока")
    void testBlockTitle() {
        assertTrue(homePage.isBlockTitleVisible(EXPECTED_BLOCK_TITLE), "Название блока некорректное");
    }

    @Test
    @Story("Платёжные системы")
    @Description("Проверяет наличие логотипов популярных платёжных систем в блоке.")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("2. Проверка логотипов платёжных систем")
    void testLogos() {
        assertTrue(homePage.arePaymentLogosPresent(EXPECTED_LOGOS), "Не все логотипы найдены");
    }

    @Test
    @Story("Информация о сервисе")
    @Description("Проверяет переход по ссылке 'Подробнее о сервисе'.")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("3. Проверка ссылки «Подробнее о сервисе»")
    void testDetailsLink() {
        String currentUrl = driver.getCurrentUrl();
        homePage.clickDetailsLink();
        assertNotEquals(currentUrl, driver.getCurrentUrl(), "Ссылка не ведет на другую страницу");
    }

    @Test
    @Story("Заполнение формы")
    @Description("Проверяет, что после ввода номера телефона происходит переход на следующий шаг.")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("4. Проверка формы и кнопки «Продолжить»")
    void testPhoneForm() {
        homePage.fillPhoneAndSubmit(TEST_PHONE_NUMBER);
        assertTrue(homePage.isAmountFieldVisible() || !driver.getCurrentUrl().equals(SITE_URL),
                "Не произошел переход после ввода номера");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
