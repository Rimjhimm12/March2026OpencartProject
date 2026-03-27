package com.qa.opencart.pages;

import com.qa.opencart.constants.ConstantsUtil;
import com.qa.opencart.utils.ElementsUtil;
import com.qa.opencart.utils.TimeUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage {

    private final WebDriver driver;
    private final ElementsUtil elementsUtil;



    /**
     *Page elements - By locators
     *
     */
    private final By EMAIL_ID = By.id("input-email");
    private final By PASSWORD = By.id("input-password");
    private final By LOGIN_BUTTON = By.xpath("//input[@type='submit']");
    private final By FORGOT_PASSWORD_LINK = By.linkText("Forgotten Password");
    private final By REGISTER_LINK = By.linkText("Register");

    /**
     * Constructor of the class
     * @param driver - WebDriver instance to interact with the browser
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        elementsUtil = new ElementsUtil(driver);
    }

    /**
     * Page actions - Methods to perform actions on the page.
     * 1. Get the title of the page*
     * 2. Get the URL of the page
     * 3. Check if forgot PASSWORD link is exist on the page
     */
    public String getLoginPageTitle(){
        String title = elementsUtil.waitForTitleToBe(ConstantsUtil.LOGIN_PAGE_TITLE,TimeUtil.DEFAULT_TIME_OUT);
        System.out.println("Login Page Title: " + title);
        return title;
    }

    public String getLoginPageURL(){
        String url = elementsUtil.waitForURLContains(ConstantsUtil.LOGIN_PAGE_URL_FRACTION, TimeUtil.DEFAULT_TIME_OUT);
        System.out.println("Login Page Title: " + url);
        return url;
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
