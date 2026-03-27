package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.SheetUtil;
import com.qa.opencart.errors.AppErrors;
import com.qa.opencart.utils.*;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegistrationPageTest extends BaseTest {


    @BeforeTest
    public void setUp(){

        registrationPage = loginPage.navigateToRegistrationPage();
    }

    @DataProvider
    public Object[][] getRegistrationData() {
        return new Object[][]{
                {"Misti", "Doi", "9876543210", "test@1223", "yes"},
                {"Rosogolla", "Doi", "9876543210", "test@1223", "no"},
                {"Ladoo", "Doi", "9876543210", "test@1223", "no"},
                {"Kalakand", "Doi", "9876543210", "test@1223", "yes"}
        };
    }

    @DataProvider
    public Object[][] getUserdata(){
        return ExcelUtil.getTestData(SheetUtil.REGISTRATION_EXCEL_SHEET_NAME);
    }

    @DataProvider
    public Object[][] getUserdataFromExcel(){
        return ExcelUtils.getRegisterUserData(SheetUtil.REGISTRATION_EXCEL_SHEET_NAME);
    }

    @DataProvider
    public Object[][] getUserdataFromCSV(){
        return CSVUtil.csvData(SheetUtil.REGISTRATION_CSV_SHEET_NAME);
    }

    @Test(dataProvider = "getUserdataFromExcel")
    public void userRegistrationTest(String firstName, String lastName, String phoneNumber, String password, String subscribe) throws InterruptedException {
        Thread.sleep(2000);
        boolean flag = registrationPage.
                userRegister(firstName, lastName, StringUtil.getRandomEmil(), phoneNumber, password, subscribe );
        Assert.assertTrue(flag, AppErrors.REGISTRATION_FAILED );
    }

}
