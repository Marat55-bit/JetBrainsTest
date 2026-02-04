package com.example.jetbrainstest.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class DriverWaitUtils {

    private static final int DEFAULT_WAIT_SECONDS = 10;

    private DriverWaitUtils() {
    }

    public static WebDriverWait getWait(WebDriver driver) {
        return getWait(driver, DEFAULT_WAIT_SECONDS);
    }

    public static WebDriverWait getWait(WebDriver driver, int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }
}
