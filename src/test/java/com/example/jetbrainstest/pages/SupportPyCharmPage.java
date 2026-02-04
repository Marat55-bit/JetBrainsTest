package com.example.jetbrainstest.pages;

import com.example.jetbrainstest.utils.DriverWaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SupportPyCharmPage {
    private static final int WAIT_SECONDS = 15;

    private final Logger log = LoggerFactory.getLogger(SupportPyCharmPage.class);
    private final WebDriver driver;

    @FindBy(xpath = "//a[contains(@href,'ticket_form_id=66731')]")
    private WebElement linkToRequestForm;

    public SupportPyCharmPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickLinkToRequestFormAndWaitForUrl(String expectedFormUrl) {
        log.info("Клик по ссылке на форму обращения");
        DriverWaitUtils.getWait(driver, WAIT_SECONDS)
                .until(ExpectedConditions.visibilityOf(linkToRequestForm))
                .click();
        log.info("Ожидание перехода на страницу формы: {}", expectedFormUrl);
        DriverWaitUtils.getWait(driver, WAIT_SECONDS)
                .until(ExpectedConditions.urlToBe(expectedFormUrl));
        log.info("Переход на страницу формы выполнен успешно");
    }

    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        log.info("Текущий URL: {}", url);
        return url;
    }
}
