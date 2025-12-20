package com.example.jetbrainstest.pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SupportPyCharmPage {
    private final WebDriver driver;

    // Виджет кнопка "Contact Support"
    @FindBy(css = "#support-widget > a.button")
    private WebElement contactSupportBtn;

    // Элемент формы на странице обращений
    @FindBy(id = "new_request_form")
    private WebElement requestForm;

    /**
     * Конструктор класса
     */
    public SupportPyCharmPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Метод кликает на кнопку "Contact Support" и проверяет наличие формы
     *
     * @return true, если страница перехода успешна и форма существует
     */
    public boolean clickContactSupportAndVerifyFormPresent() {
        System.out.println("Нажатие на кнопку 'Contact Support'...");

        // Ждем появление кнопки и кликаем на нее
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(contactSupportBtn)).click();

        // Пауза 5 секунд, чтобы убедиться, что форма появилась
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignored) {
        }

        // Проверяем наличие формы
        if (!requestForm.isDisplayed()) {
            Allure.addAttachment("Форма отсутствует", driver.getPageSource());
            return false;
        }

        return true;
    }


      //Получаем current URL

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}