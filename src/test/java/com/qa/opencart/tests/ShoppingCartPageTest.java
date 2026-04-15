package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.ConstantsUtil;
import com.qa.opencart.errors.AppErrors;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ShoppingCartPageTest extends BaseTest {

    @BeforeTest
    public void setUp() throws InterruptedException {
        accountPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
        searchResultPage = accountPage.doSearch("MacBook");
        productInfoPage = searchResultPage.selectProductFromResults("MacBook Pro");
        Thread.sleep(1000);
        shoppingCartPage = productInfoPage.isProductAddedToCart();
    }

    @Test
    public void shoppingCartPageTitleTest() {
        String title = shoppingCartPage.getShoppingCartPageTitle();
        Assert.assertEquals(title, ConstantsUtil.SHOPPING_CART_PAGE_TITLE, AppErrors.TITLE_NOT_FOUND);
    }

}
