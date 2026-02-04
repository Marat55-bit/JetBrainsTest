package com.example.jetbrainstest.tests;

import com.example.jetbrainstest.pages.FleetPage;
import com.example.jetbrainstest.pages.PyCharmPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FleetTest extends BaseTest {

    private FleetPage FleetPage;

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        getDriver().get("https://www.jetbrains.com/fleet/");
        FleetPage = new FleetPage(getDriver());
    }

    @Test
    @DisplayName("Проверка, что кнопка скачивания активна")
    public void buttonCheck() {
        assertTrue(FleetPage.checkIfDownloadButtonIsClickable(), "Кнопка скачивания не активна");
    }

    @Test
    @DisplayName("Проверка, что кнопка 'Distributed' активна")
    public void checkIfDistributedButtonIsClickable(){
        assertTrue(FleetPage.checkIfDistributedButtonIsClickable(), "Кнопка 'Distributed' не активна");
        }
    }
