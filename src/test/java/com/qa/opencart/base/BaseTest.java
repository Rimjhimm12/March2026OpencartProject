package com.qa.opencart.base;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import java.util.Properties;


public class BaseTest {

    DriverFactory df;
    WebDriver driver;
    protected Properties prop;
    protected LoginPage loginPage;
    protected AccountPage accountPage;
    protected SearchResultPage searchResultPage;
    protected ProductInfoPage productInfoPage;
    protected ShoppingCartPage shoppingCartPage;
    protected RegistrationPage registrationPage;
    protected SoftAssert softAssert;




    @Parameters({"browser","browserversion"})
    @BeforeTest
    public void setup(@Optional("chrome") String browserName, @Optional("") String browserVersion) {
        df = new DriverFactory();
        prop = df.initProp();
        if(browserName!= null){
            prop.setProperty("browser", browserName);
        }
        if(browserVersion != null){
            prop.setProperty("browserversion", browserVersion);
        }
        softAssert = new SoftAssert();
        driver = df.initDriver(prop); //call by reference
        loginPage = new LoginPage(driver);




    }

    @AfterTest
    public  void tearDown(){
        driver.quit();
    }
}
