package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchResultPageTest extends BaseTest {


    @BeforeTest
    public void setUp(){
            accountPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
        }

    @DataProvider
    public Object[][] itemSearch(){
        return new Object[][]{
                {"MacBook"},
                {"iMac"},
                {"Apple"}

        };
    }

    @Test(priority = 1 , dataProvider = "itemSearch")
    public void searchResultCountTest(String searchItem)  {
        searchResultPage = accountPage.doSearch(searchItem);
        //Thread.sleep(1000);
        int searchResultCount = searchResultPage.getSearchResultCount();
        Assert.assertTrue(searchResultCount > 0);
    }

     @DataProvider
     public Object[][] searchedData(){
         return new Object[][]{
                 {"MacBook","MacBook Air"},
                 {"Mac","MacBook Pro"},
                 {"imac","iMac"}
         };
     }

     @Test(priority = 2, dataProvider = "searchedData")
     public void selectProductFromResults(String searchItem,String selectProduct) throws InterruptedException {
         searchResultPage = accountPage.doSearch(searchItem);
         Thread.sleep(1000);
         productInfoPage = searchResultPage.selectProductFromResults(selectProduct);
         Assert.assertEquals(productInfoPage.getProductInfo(), selectProduct);
     }


}
