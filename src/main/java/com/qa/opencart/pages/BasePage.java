package com.qa.opencart.pages;

import com.qa.opencart.utils.ElementsUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    protected WebDriver driver;
    protected ElementsUtil elementsUtil;

    private static final By LOGO = By.cssSelector("div#logo a");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.elementsUtil = new ElementsUtil(driver);
    }

    public abstract String getPageTitle();

    public abstract String getPageURL();

    public abstract String getPageType();

    public boolean isLogoDisplayed() {
        return elementsUtil.doIsDisplayed(LOGO);
    }
}