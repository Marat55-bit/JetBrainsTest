package com.example.jetbrainstest.tests;

import com.example.jetbrainstest.pages.SupportPyCharmPage;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SupportPyCharmTest extends BaseTest {

    private SupportPyCharmPage supportPyCharmPage;

    @BeforeEach
    public void setup() {
        super.setUp(); // Устанавливаем драйвер и браузер
        supportPyCharmPage = new SupportPyCharmPage(getDriver()); // Инициализируем страницу
    }

    @Test
    @Order(1)
    @DisplayName("Тестируем переход на контактную форму поддержки")
    public void testContactSupportFormTransition() {
        // Переходим на главную страницу поддержки
        getDriver().get("https://intellij-support.jetbrains.com/hc/en-us/?pycharm");

        // Кликаем на кнопку "Contact Support" и проверяем наличие формы
        boolean formExists = supportPyCharmPage.clickContactSupportAndVerifyFormPresent();
        assertTrue(formExists, "Форма поддержки отсутствует!");

        // Проверяем URL после перехода
        String expectedUrl = "https://intellij-support.jetbrains.com/hc/en-us/requests/new?ticket_form_id=66731";
        String actualUrl = supportPyCharmPage.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl, "Переход на неправильный URL!");
    }
}