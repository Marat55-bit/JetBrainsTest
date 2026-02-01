package com.example.jetbrainstest.pages;

import com.example.jetbrainstest.MyWait;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class SupportPyCharmPage {
    private static final int WAIT_SECONDS = 15;
    private static final int COOKIE_MODAL_WAIT_SECONDS = 5;
    private static final int COOKIE_MODAL_DISAPPEAR_SECONDS = 5;
    private static final By COOKIE_DIALOG = By.id("ch2-dialog");
    private final Logger LOG = LoggerFactory.getLogger(SupportPyCharmPage.class);
    private final WebDriver driver;

    /** Ссылка на форму создания обращения (переход на страницу с ticket_form_id=66731) */
    @FindBy(xpath = "/html/body/div[3]/div[1]/div[1]/div/div[2]/div[1]/a")
    private WebElement linkToRequestForm;

    public SupportPyCharmPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Закрывает модальное окно "Cookie Settings", если оно отображается (клик по крестику).
     * Не падает, если окно отсутствует.
     */
    public void closeCookieModalIfPresent() {
        LOG.info("Проверка и закрытие модального окна Cookie Settings");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(COOKIE_MODAL_WAIT_SECONDS));
        List<By> closeButtonLocators = Arrays.asList(
                By.xpath("//*[@id='ch2-dialog']/div[3]/button[1]"),
                By.cssSelector("[aria-label='Close']"),
                By.cssSelector("button[aria-label='Close']"),
                By.cssSelector("[role='dialog'] button[aria-label='Close']"),
                By.cssSelector(".close"),
                By.cssSelector("#onetrust-close-btn-handler")
        );
        for (By locator : closeButtonLocators) {
            try {
                WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(locator));
                if (closeBtn != null && closeBtn.isDisplayed()) {
                    closeBtn.click();
                    LOG.info("Модальное окно Cookie Settings закрыто");
                    waitForCookieModalToDisappear();
                    return;
                }
            } catch (TimeoutException ignored) {
                // элемент не найден за время ожидания — пробуем следующий селектор
            }
        }
        LOG.info("Модальное окно Cookie Settings не отображается или уже закрыто");
    }

    /** Ждёт исчезновения модального окна Cookie Settings, чтобы не блокировать виджет поддержки. */
    private void waitForCookieModalToDisappear() {
        LOG.info("Ожидание исчезновения модального окна Cookie");
        try {
            WebDriverWait disappearWait = new WebDriverWait(driver, Duration.ofSeconds(COOKIE_MODAL_DISAPPEAR_SECONDS));
            disappearWait.until(ExpectedConditions.invisibilityOfElementLocated(COOKIE_DIALOG));
        } catch (TimeoutException ignored) {
            // модалка уже могла быть закрыта или не показывалась
        }
    }

    /**
     * Кликает по ссылке на форму обращения и ждёт перехода на указанный URL.
     * Тест завершается проверкой URL, без ожидания элементов формы на странице.
     *
     * @param expectedFormUrl ожидаемый URL страницы формы после перехода
     */
    public void clickLinkToRequestFormAndWaitForUrl(String expectedFormUrl) {
        LOG.info("Клик по ссылке на форму обращения");
        MyWait.myWait(WAIT_SECONDS).visible(linkToRequestForm).click();
        LOG.info("Ожидание перехода на страницу формы: {}", expectedFormUrl);
        WebDriverWait urlWait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS));
        urlWait.until(ExpectedConditions.urlToBe(expectedFormUrl));
        LOG.info("Переход на страницу формы выполнен успешно");
    }

    /** Возвращает текущий URL страницы */
    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        LOG.info("Текущий URL: {}", url);
        return url;
    }
}