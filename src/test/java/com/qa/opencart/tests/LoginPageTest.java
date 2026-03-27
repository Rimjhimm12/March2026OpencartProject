package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.ConstantsUtil;
import com.qa.opencart.errors.AppErrors;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Test(priority = 1)
    public void loginPageTitleTest(){
        String title = loginPage.getLoginPageTitle();
        Assert.assertEquals(title,ConstantsUtil.LOGIN_PAGE_TITLE, AppErrors.TITLE_NOT_FOUND );
    }

    @Test(priority = 2)
    public void loginPageURLTest(){
        String url = loginPage.getLoginPageURL();
        Assert.assertTrue(url.contains(ConstantsUtil.LOGIN_PAGE_URL_FRACTION), AppErrors.URL_NOT_FOUND);
    }

    @Test(priority = 3)
    public void loginPageForgotPasswordLinkTest(){
        Assert.assertTrue(loginPage.isExistForgotPassword(), AppErrors.ELEMENT_NOT_FOUND);
     }

    @Test(priority = 4)
    public void loginTest() {
       accountPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
        Assert.assertEquals(accountPage.getAccountPageTitle(),ConstantsUtil.ACCOUNT_PAGE_TITLE, AppErrors.ELEMENT_NOT_FOUND);

    }

}
