package com.qa.opencart.pages;

import com.qa.opencart.constants.ConstantsUtil;
import com.qa.opencart.utils.ElementsUtil;
import com.qa.opencart.utils.TimeUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class RegistrationPage {

    private final WebDriver driver;
    private final ElementsUtil elementsUtil;


    private final By FIRST_NAME = By.id("input-firstname");
    private final By LAST_NAME = By.id("input-lastname");
    private final By EMAIL_ID = By.id("input-email");
    private final By TELEPHONE = By.id("input-telephone");
    private final By PASSWORD = By.id("input-password");
    private final By CONFIRM_PASSWORD = By.id("input-confirm");
    private final By SUBSCRIBE_YES = By.xpath("//input[@name='newsletter' and @value='1']");
    private final By SUBSCRIBE_NO = By.xpath("//input[@name='newsletter' and @value='0']");
    private final By PRIVACY_POLICY_CHECKBOX = By.name("agree");
    private final By CONTINUE_BUTTON = By.xpath("//input[@type='submit']");
    private final By SUCCESS_MESSAGE = By.cssSelector("#content h1");
    private final By LOGOUT_LINK = By.linkText("Logout");
    private final By REGISTER_LINK = By.linkText("Register");


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        elementsUtil = new ElementsUtil(driver);
    }


    public boolean userRegister(String firstName, String lastName, String email, String telephone, String password,
                                String subscribe) {

        elementsUtil.doSendKeys(FIRST_NAME, firstName, TimeUtil.DEFAULT_TIME_OUT);
        elementsUtil.doSendKeys(LAST_NAME, lastName);
        elementsUtil.doSendKeys(EMAIL_ID, email);
        elementsUtil.doSendKeys(TELEPHONE, telephone);
        elementsUtil.doSendKeys(PASSWORD, password);
        elementsUtil.doSendKeys(CONFIRM_PASSWORD, password);

        if (subscribe.equalsIgnoreCase("yes")) {
            elementsUtil.doClick(SUBSCRIBE_YES);
        } else {
            elementsUtil.doClick(SUBSCRIBE_NO);
        }

        elementsUtil.doClick(PRIVACY_POLICY_CHECKBOX);
        elementsUtil.doClick(CONTINUE_BUTTON);

        String successMessage = elementsUtil.waitForElementVisible(SUCCESS_MESSAGE, TimeUtil.DEFAULT_TIME_OUT).getText();

        System.out.println(successMessage);

        if (successMessage.contains(ConstantsUtil.REGISTRATION_SUCCESS_MESSAGE)) {
            elementsUtil.doClick(LOGOUT_LINK);
            elementsUtil.doClick(REGISTER_LINK);
            return true;
        } else {
            return false;
        }

    }
}
