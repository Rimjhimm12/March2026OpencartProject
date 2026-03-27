package com.qa.opencart.pages;

import com.qa.opencart.constants.ConstantsUtil;
import com.qa.opencart.utils.ElementsUtil;
import com.qa.opencart.utils.TimeUtil;
import org.openqa.selenium.WebDriver;

public class ShoppingCartPage {

    private final   WebDriver driver;
    private final ElementsUtil elementsUtil;

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        elementsUtil = new ElementsUtil(driver);
    }

    public String getShoppingCartPageTitle(){
        return elementsUtil.waitForTitleToBe(ConstantsUtil.SHOPPING_CART_PAGE_TITLE, TimeUtil.DEFAULT_TIME_OUT);
    }


}
