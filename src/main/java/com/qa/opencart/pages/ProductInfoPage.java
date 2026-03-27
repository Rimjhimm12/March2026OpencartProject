package com.qa.opencart.pages;

import com.qa.opencart.utils.ElementsUtil;
import com.qa.opencart.utils.TimeUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ProductInfoPage {
    private final WebDriver driver;
    private final ElementsUtil elementsUtil;
    private Map<String, String> metaData;

    private final By PRODUCT_NAME = By.cssSelector("div#content h1");
    private final By PRODUCT_IMAGE = By.cssSelector("ul.thumbnails img");
    private final By PRODUCT_META_DATA = By.xpath("(//div[@id = \"content\"]//ul[@class = \"list-unstyled\"])[1]/li");
    private final By PRODUCT_PRICE_DATA = By.xpath("(//div[@id = \"content\"]//ul[@class = \"list-unstyled\"])[2]/li");
    private final By PRODUCT_QUANTITY = By.id("input-quantity");
    private final By ADD_TO_CART_BUTTON = By.id("button-cart");
    private final By SUCCESS_MESSAGE = By.xpath("//div[@id='product-product']/div[contains(text(),'Success')]");
    private final By SHOPPING_CART_LINK = By.xpath("//div[@id='product-product']//a[text()='shopping cart']");




    public ProductInfoPage(WebDriver driver){
        this.driver = driver;
        elementsUtil = new ElementsUtil(driver);
    }

    public String getProductInfo(){

        return elementsUtil.doGetText(PRODUCT_NAME);
    }

    public int getProductImageCount(){
         return elementsUtil.waitForVisibilityOfElementsLocated(PRODUCT_IMAGE, TimeUtil.DEFAULT_TIME_OUT).size();
    }

    private void getProductMetaData(){
        List<WebElement> productMetaData = elementsUtil.waitForVisibilityOfElementsLocated(PRODUCT_META_DATA, TimeUtil.DEFAULT_TIME_OUT);
        for(WebElement e: productMetaData){
            String text = e.getText();
            String[] meta = text.split(":");
            String key = meta[0].trim();
            String value = meta[1].trim();
            metaData.put(key, value);
        }

    }

    private void getProductPriceData(){
        List<WebElement> productPrice = elementsUtil.waitForVisibilityOfElementsLocated(PRODUCT_PRICE_DATA, TimeUtil.DEFAULT_TIME_OUT);
        String actualPrice = productPrice.get(0).getText();
        String discountPrice = productPrice.get(1).getText().split(":")[1].trim();
        metaData.put("Actual Price: ", actualPrice);
        metaData.put("Discount Price: ", discountPrice);
        }


    public Map<String, String> getProductInfoMap(){
        metaData = new TreeMap<>();
        metaData.put("Product Name: ", getProductInfo());
        getProductMetaData();
        getProductPriceData();
        return metaData;
    }

    public ShoppingCartPage isProductAddedToCart() {
        elementsUtil.doSendKeys(PRODUCT_QUANTITY, "2");
        elementsUtil.doClick(ADD_TO_CART_BUTTON);
        String text = elementsUtil.waitForElementVisible(SUCCESS_MESSAGE, TimeUtil.DEFAULT_TIME_OUT).getText();
        if(text.contains("Success")){
            elementsUtil.doClick(SHOPPING_CART_LINK);
            return new ShoppingCartPage(driver);
        }
        else
            return null;

    }

}
