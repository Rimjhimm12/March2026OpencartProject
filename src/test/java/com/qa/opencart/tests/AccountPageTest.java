package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.ConstantsUtil;
import com.qa.opencart.errors.AppErrors;
import com.qa.opencart.listeners.TestAllureListener;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestAllureListener.class})
public class AccountPageTest extends  BaseTest {

    @BeforeTest
    public void accountPageSetup(){
        accountPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
    }

    @Test(priority = 1)
    public void accountPageTitleTest(){
        String title = accountPage.getAccountPageTitle();
        Assert.assertEquals(title, ConstantsUtil.ACCOUNT_PAGE_TITLE, AppErrors.TITLE_NOT_FOUND);
    }

    @Test(priority = 2)
    public void accountPageURLTest(){
        String url = accountPage.getAccountPageURL();
        Assert.assertTrue(url.contains(ConstantsUtil.ACCOUNT_PAGE_URL), AppErrors.URL_NOT_FOUND);
    }

    @Test(priority = 3)
    public void accountPageLogoImageText(){
        Boolean isLogoExist = accountPage.isLogoLinkExist();
        Assert.assertTrue(isLogoExist, AppErrors.ELEMENT_NOT_FOUND);
    }

    @Test(priority = 4)
    public void AccountPageHeadersTest(){
        Assert.assertEquals
                (accountPage.getAccountPageHeaders(), ConstantsUtil.ACCOUNT_PAGE_HEADERS_LIST, AppErrors.LIST_NOT_MATCHED);

    }

    @DataProvider
    public Object[][] accountPageDataProvider(){
        return new Object[][]{
                {"MacBook", 3},
                {"iMac", 1},
                {"Apple", 1},
                {"iPhone", 1},
                {"Nikon", 1},
                {"Canon", 1},
                {"Samsung", 2}
        };
    }

    @Test(priority = 5, dataProvider = "accountPageDataProvider")
    public void AccountPageProductSearchTest(String productName, int productCount){
        searchResultPage = accountPage.doSearch(productName);
        Assert.assertEquals(searchResultPage.getSearchResultCount(),productCount, AppErrors.PRODUCT_COUNT_NOT_MATCHED);
    }

}
