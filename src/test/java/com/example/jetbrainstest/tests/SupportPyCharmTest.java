package com.example.jetbrainstest.tests;

import com.example.jetbrainstest.pages.CookieConsentPage;
import com.example.jetbrainstest.pages.SupportPyCharmPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SupportPyCharmTest extends BaseTest {

    private static final String SUPPORT_PAGE_URL =
            "https://intellij-support.jetbrains.com/hc/en-us/?pycharm";
    private static final String EXPECTED_FORM_URL =
            "https://intellij-support.jetbrains.com/hc/en-us/requests/new?ticket_form_id=66731";

    private SupportPyCharmPage supportPyCharmPage;
    private CookieConsentPage cookieConsentPage;

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        supportPyCharmPage = new SupportPyCharmPage(getDriver());
        cookieConsentPage = new CookieConsentPage(getDriver());
    }

    @Test
    @DisplayName("Тестируем переход на контактную форму поддержки")
    void testContactSupportFormTransition() {
        getDriver().get(SUPPORT_PAGE_URL);
        cookieConsentPage.closeCookieModalIfPresent();
        supportPyCharmPage.clickLinkToRequestFormAndWaitForUrl(EXPECTED_FORM_URL);
        assertEquals(EXPECTED_FORM_URL, supportPyCharmPage.getCurrentUrl(),
                "Страница должна открыться по адресу формы обращения");
    }
}
