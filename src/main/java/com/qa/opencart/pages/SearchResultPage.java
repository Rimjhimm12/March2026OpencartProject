package com.qa.opencart.pages;

import com.qa.opencart.utils.ElementsUtil;
import com.qa.opencart.utils.TimeUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class SearchResultPage {

    private final WebDriver driver;
    private final ElementsUtil elementsUtil;

    private final By SEARCH_RESULT_COUNT  = By.cssSelector("div.product-thumb");

    public SearchResultPage(WebDriver driver){
        this.driver = driver;
        elementsUtil = new ElementsUtil(driver);
    }

    public int getSearchResultCount() {
            return elementsUtil.waitForVisibilityOfElementsLocated
                    (SEARCH_RESULT_COUNT, TimeUtil.DEFAULT_TIME_OUT).size();
    }

    public ProductInfoPage selectProductFromResults(String selectProduct){
        By productLink = By.xpath("//div[contains(@class,'product-thumb')]//a[normalize-space()='" + selectProduct + "']");
        if (getSearchResultCount() == 0) {
            System.out.println("Product is not found in the search result: " + selectProduct);
            return null;
        }
        elementsUtil.clickWhenReady(productLink, TimeUtil.DEFAULT_TIME_OUT);
        return new ProductInfoPage(driver);
    }

}
