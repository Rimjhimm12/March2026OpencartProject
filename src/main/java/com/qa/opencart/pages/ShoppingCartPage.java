package com.qa.opencart.pages;

import com.qa.opencart.constants.ConstantsUtil;
import com.qa.opencart.utils.TimeUtil;
import org.openqa.selenium.WebDriver;

public class ShoppingCartPage extends BasePage {

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getPageTitle() {
        return elementsUtil.waitForTitleToBe(ConstantsUtil.SHOPPING_CART_PAGE_TITLE, TimeUtil.DEFAULT_TIME_OUT);
    }

    @Override
    public String getPageURL() {
        return driver.getCurrentUrl();
    }

    @Override
    public String getPageType() {
        return "Shopping Cart Page";
    }


}
