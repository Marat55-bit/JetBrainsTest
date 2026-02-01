package com.example.jetbrainstest.tests;

import com.example.jetbrainstest.pages.SupportPyCharmPage;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SupportPyCharmTest extends BaseTest {

    private static final String SUPPORT_PAGE_URL =
            "https://intellij-support.jetbrains.com/hc/en-us/?pycharm";
    private static final String EXPECTED_FORM_URL =
            "https://intellij-support.jetbrains.com/hc/en-us/requests/new?ticket_form_id=66731";

    private SupportPyCharmPage supportPyCharmPage;

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        supportPyCharmPage = new SupportPyCharmPage(getDriver());
    }

    @Test
    @Order(1)
    @DisplayName("Тестируем переход на контактную форму поддержки")
    public void testContactSupportFormTransition() {
        getDriver().get(SUPPORT_PAGE_URL);
        supportPyCharmPage.closeCookieModalIfPresent();

        supportPyCharmPage.clickLinkToRequestFormAndWaitForUrl(EXPECTED_FORM_URL);

        assertEquals(EXPECTED_FORM_URL, supportPyCharmPage.getCurrentUrl(),
                "Страница должна открыться по адресу формы обращения");
    }
}