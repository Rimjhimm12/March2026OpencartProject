package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.ConstantsUtil;
import com.qa.opencart.errors.AppErrors;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class ProductInfoPageTest extends BaseTest {

    @BeforeTest
    public void setUp(){
        accountPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));


    }

    @DataProvider
    public Object[][] productData(){
        return new Object[][]{
                {"MacBook", "MacBook Pro"},
                {"MacBook", "MacBook Air"}
        };

    }

    @Test(dataProvider = "productData")
    public void productInfoTest(String productName, String mainProductName) {
        searchResultPage = accountPage.doSearch(productName);
        productInfoPage = searchResultPage.selectProductFromResults(mainProductName);
        String productHeader = productInfoPage.getProductInfo();
        Assert.assertEquals(productHeader, mainProductName);
    }

    @DataProvider
    public Object[][] productDataSet(){
        return new Object[][]{
                {"MacBook", "MacBook Pro", 4},
                {"MacBook", "MacBook Air", 4},
                {"MacBook", "MacBook", 5}
        };

    }

     @Test(dataProvider = "productDataSet")
     public void productImageCountTest(String productName, String mainProductName, int count) {
         searchResultPage = accountPage.doSearch(productName);
         productInfoPage = searchResultPage.selectProductFromResults(mainProductName);
         int imageCount = productInfoPage.getProductImageCount();
         Assert.assertEquals(imageCount, count, AppErrors.IMAGE_COUNT_NOT_MATCHED);
     }

     @Test
     public  void  productMetaDataTest(){
         searchResultPage = accountPage.doSearch("MacBook");
         productInfoPage = searchResultPage.selectProductFromResults("MacBook Pro");
         Map<String, String> metaDataList = productInfoPage.getProductInfoMap();
         System.out.println("Product meta data list: " + metaDataList);
         softAssert.assertEquals(metaDataList.get("Product Name: "), "MacBook Pro");
         softAssert.assertEquals(metaDataList.get("Brand"), "Apple");
         softAssert.assertEquals(metaDataList.get("Product Code"), "Product 18");
         softAssert.assertEquals(metaDataList.get("Reward Points"), "800");
         softAssert.assertEquals(metaDataList.get("Availability"), "Out Of Stock");
         softAssert.assertEquals(metaDataList.get("Actual Price: "), "$2,000.00");
         softAssert.assertEquals(metaDataList.get("Discount Price: "), "$2,000.00");
         softAssert.assertAll();


     }


     @Test
     public void productAddedToCartTest() throws InterruptedException {
         searchResultPage = accountPage.doSearch("MacBook");
         productInfoPage = searchResultPage.selectProductFromResults("MacBook Pro");
         Thread.sleep(1000);
         shoppingCartPage = productInfoPage.isProductAddedToCart();
         String title = shoppingCartPage.getShoppingCartPageTitle();
         Assert.assertEquals(title, ConstantsUtil.SHOPPING_CART_PAGE_TITLE, AppErrors.TITLE_NOT_FOUND);
     }



}
