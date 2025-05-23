package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MtsHomePage;

import static org.junit.jupiter.api.Assertions.*;

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
    @DisplayName("1. Проверка названия блока")
    void testBlockTitle() {
        assertTrue(homePage.isBlockTitleVisible(EXPECTED_BLOCK_TITLE), "Название блока некорректное");
    }

    @Test
    @DisplayName("2. Проверка логотипов платёжных систем")
    void testLogos() {
        assertTrue(homePage.arePaymentLogosPresent(EXPECTED_LOGOS), "Не все логотипы найдены");
    }

    @Test
    @DisplayName("3. Проверка ссылки «Подробнее о сервисе»")
    void testDetailsLink() {
        String currentUrl = driver.getCurrentUrl();
        homePage.clickDetailsLink();
        Assertions.assertNotEquals(currentUrl, driver.getCurrentUrl(), "Ссылка не ведет на другую страницу");
    }

    @Test
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
