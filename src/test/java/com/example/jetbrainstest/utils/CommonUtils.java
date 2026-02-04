package com.example.jetbrainstest.utils;

public final class CommonUtils {

    private CommonUtils() {
    }

    public static void pause(long durationMillis) {
        try {
            Thread.sleep(durationMillis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    public static void pauseSeconds(int seconds) {
        pause(seconds * 1000L);
    }
}
