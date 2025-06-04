package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import io.qameta.allure.Step;

public class MtsHomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private final By blockTitle = By.xpath("//*[@class=\"pay__wrapper\"]/h2");
    private final By paymentLogos = By.xpath("//*[@class=\"pay__partners\"]/ul/li");
    private final By detailsLink = By.xpath("//*[@class=\"pay__wrapper\"]/a");
    private final By phoneInput = By.xpath("//*[@id=\"connection-phone\"]");
    private final By continueButton = By.xpath("//*[@id=\"pay-connection\"]/button");
    private final By amountField = By.xpath("//*[@id=\"connection-sum\"]");
    private final By cookieButton = By.xpath("//button[contains(text(), 'Принять') or contains(text(), 'Согласен')]");

    public MtsHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Step("Открываем сайт {url}")
    public void open(String url) {
        driver.get(url);
        driver.manage().window().maximize();

    }

    @Step("Проверка, что заголовок блока равен '{expectedText}'")
    public boolean isBlockTitleVisible(String expectedText) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(blockTitle));
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.isDisplayed() && element.getText().equals(expectedText);
    }

    @Step("Проверка наличия логотипов: {expectedLogos}")
    public boolean arePaymentLogosPresent(String[] expectedLogos) {
        List<WebElement> logos = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(paymentLogos));
        if (logos.size() != expectedLogos.length) return false;

        for (String name : expectedLogos) {
            try {
                WebElement logo = driver.findElement(By.xpath("//img[contains(@alt, '" + name + "')]"));
                if (!logo.isDisplayed()) return false;
            } catch (NoSuchElementException e) {
                return false;
            }
        }
        return true;
    }

    @Step("Нажимаем на ссылку «Подробнее о сервисе»")
    public void clickDetailsLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(detailsLink));
        link.click();
    }

    @Step("Вводим номер телефона: {phoneNumber} и нажимаем 'Продолжить'")
    public void fillPhoneAndSubmit(String phoneNumber) {
        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneInput));
        phoneField.clear();
        phoneField.sendKeys(phoneNumber);

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        button.click();
    }

    @Step("Проверка, что появилось поле суммы")
    public boolean isAmountFieldVisible() {
        try {
            WebElement amount = wait.until(ExpectedConditions.visibilityOfElementLocated(amountField));
            return amount.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}
