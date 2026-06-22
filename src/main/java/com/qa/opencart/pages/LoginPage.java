package com.qa.opencart.pages;

import com.qa.opencart.constants.ConstantsUtil;
import com.qa.opencart.utils.TimeUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage extends BasePage {

    private final By EMAIL_ID = By.id("input-email");
    private final By PASSWORD = By.id("input-password");
    private final By LOGIN_BUTTON = By.xpath("//input[@type='submit']");
    private final By FORGOT_PASSWORD_LINK = By.linkText("Forgotten Password");
    private final By REGISTER_LINK = By.linkText("Register");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getPageTitle() {
        String title = elementsUtil.waitForTitleToBe(ConstantsUtil.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_TIME_OUT);
        System.out.println("Login Page Title: " + title);
        return title;
    }

    @Override
    public String getPageURL() {
        String url = elementsUtil.waitForURLContains(ConstantsUtil.LOGIN_PAGE_URL_FRACTION, TimeUtil.DEFAULT_TIME_OUT);
        System.out.println("Login Page URL: " + url);
        return url;
    }

    @Override
    public String getPageType() {
        return "Login Page";
    }

    public Boolean isExistForgotPassword(){
        Boolean flag = elementsUtil.doIsDisplayed(FORGOT_PASSWORD_LINK);
        if (flag){
            System.out.println("Forgot Password link is displayed on the page");

        } else {
            System.out.println("Forgot Password link is not displayed on the page");

        }
        return flag;
    }

    public AccountPage doLogin(String username, String pwd){
        elementsUtil.doSendKeys(EMAIL_ID, username, TimeUtil.DEFAULT_TIME_OUT);
        elementsUtil.doSendKeys(PASSWORD,pwd);
        elementsUtil.doClick(LOGIN_BUTTON);
        return new AccountPage(driver);
     }

     public RegistrationPage navigateToRegistrationPage(){
        elementsUtil.doClick(REGISTER_LINK);
        return new RegistrationPage(driver);
     }




}
