package com.example.jetbrainstest.pages;

import com.example.jetbrainstest.AllureLogger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.slf4j.LoggerFactory;

public class PyCharmPage {

    private final AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(PyCharmPage.class));

    WebDriver driver;
    @FindBy(css = ".menu-second-title-box__title.wt-h3")
    private WebElement pyCharmButton;

    @FindBy(css = ".overview-header__download")
    private WebElement downloadButton;
    public boolean downloadButtonIsActive(){
        LOG.infoWithScreenshot("Проверка доступности кнопки 'Download'");
        driver.get("https://www.jetbrains.com/pycharm/");
        return downloadButton.isDisplayed();
    }
    public boolean pyCharmButtonIsActive(){
        driver.get("https://www.jetbrains.com/pycharm/");
        LOG.infoWithScreenshot("Проверка доступности кнопки 'PyCharm'");
        return pyCharmButton.isDisplayed();
    }
    public String clickPyCharmButtonAndCheckUrl(){
        driver.get("https://www.jetbrains.com/pycharm/");
        pyCharmButton.click();
        LOG.infoWithScreenshot("Проверка URL страницы, на которую попадаем после нажатия кнопки 'PyCharm'");
        return driver.getCurrentUrl();
    }


    public PyCharmPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
}