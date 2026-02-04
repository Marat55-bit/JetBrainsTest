package com.example.jetbrainstest.pages;

import com.example.jetbrainstest.utils.DriverWaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class CookieConsentPage {
    private static final int COOKIE_MODAL_WAIT_SECONDS = 5;
    private static final int COOKIE_MODAL_DISAPPEAR_SECONDS = 5;
    private static final By COOKIE_DIALOG = By.id("ch2-dialog");

    private final Logger log = LoggerFactory.getLogger(CookieConsentPage.class);
    private final WebDriver driver;

    private static final List<By> CLOSE_BUTTON_LOCATORS = Arrays.asList(
            By.xpath("//*[@id='ch2-dialog']/div[3]/button[1]"),
            By.cssSelector("[aria-label='Close']"),
            By.cssSelector("button[aria-label='Close']"),
            By.cssSelector("[role='dialog'] button[aria-label='Close']"),
            By.cssSelector(".close"),
            By.cssSelector("#onetrust-close-btn-handler")
    );

    public CookieConsentPage(WebDriver driver) {
        this.driver = driver;
    }

    public void closeCookieModalIfPresent() {
        log.info("Проверка и закрытие модального окна Cookie Settings");
        WebDriverWait wait = DriverWaitUtils.getWait(driver, COOKIE_MODAL_WAIT_SECONDS);
        for (By locator : CLOSE_BUTTON_LOCATORS) {
            try {
                WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(locator));
                if (closeBtn != null && closeBtn.isDisplayed()) {
                    closeBtn.click();
                    log.info("Модальное окно Cookie Settings закрыто");
                    waitForCookieModalToDisappear();
                    return;
                }
            } catch (TimeoutException ignored) {
                // локатор не подошёл — пробуем следующий из списка
            }
        }
        log.info("Модальное окно Cookie Settings не отображается или уже закрыто");
    }

    private void waitForCookieModalToDisappear() {
        log.info("Ожидание исчезновения модального окна Cookie");
        try {
            DriverWaitUtils.getWait(driver, COOKIE_MODAL_DISAPPEAR_SECONDS)
                    .until(ExpectedConditions.invisibilityOfElementLocated(COOKIE_DIALOG));
        } catch (TimeoutException ignored) {
            // диалог уже скрыт или не отображался
        }
    }
}
