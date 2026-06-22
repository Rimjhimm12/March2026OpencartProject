package com.qa.opencart.pages;

import com.qa.opencart.constants.ConstantsUtil;
import com.qa.opencart.utils.TimeUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;
import java.util.List;


public class AccountPage extends BasePage {

    private final By HEADERS_LINK = By.cssSelector("div#content h2");
    private final By SEARCH_FIELD = By.cssSelector("div#search input");
    private final By SEARCH_ICON = By.cssSelector("div#search button");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getPageTitle() {
        String title = elementsUtil.waitForTitleToBe(ConstantsUtil.ACCOUNT_PAGE_TITLE, TimeUtil.DEFAULT_TIME_OUT);
        System.out.println("Account Page Title: " + title);
        return title;
    }

    @Override
    public String getPageURL() {
        String url = elementsUtil.waitForURLContains(ConstantsUtil.ACCOUNT_PAGE_URL, TimeUtil.DEFAULT_TIME_OUT);
        System.out.println("Account Page URL: " + url);
        return url;
    }

    @Override
    public String getPageType() {
        return "Account Page";
    }

    public List<String> getAccountPageHeaders(){
       List<WebElement> headerLinks= elementsUtil.waitForVisibilityOfElementsLocated(HEADERS_LINK, TimeUtil.DEFAULT_TIME_OUT);
       List<String> listOfHeaders=new ArrayList<>();
       for(WebElement e: headerLinks){
           String text = e.getText();
           listOfHeaders.add(text);
       }
       return listOfHeaders;
    }

    public Boolean isSearchFieldExist(){
        return elementsUtil.doIsDisplayed(SEARCH_FIELD);
    }

    public SearchResultPage doSearch(String productName){
        if (isSearchFieldExist()) {
            elementsUtil.doSendKeys(SEARCH_FIELD, productName, TimeUtil.DEFAULT_TIME_OUT);
            elementsUtil.doClick(SEARCH_ICON, TimeUtil.DEFAULT_TIME_OUT);
            return new SearchResultPage(driver);
        }
        else
            return null;
        }


}


