package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class ConstantsUtil {
    private ConstantsUtil(){}

    public static final String CONFIG_FILE_PATH = "./src/resources/config/config.properties";
    public static final String QA_FILE_PATH = "./src/resources/config/qa.properties";
    public static final String DEV_FILE_PATH = "./src/resources/config/dev.properties";
    public static final String STAGE_FILE_PATH = "./src/resources/config/stage.properties";
    public static final String UAT_FILE_PATH = "./src/resources/config/uat.properties";

    public static final String LOGIN_PAGE_TITLE = "Account Login";
    public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login";
    public static final String ACCOUNT_PAGE_TITLE = "My Account";
    public static final String ACCOUNT_PAGE_URL = "route=account/account";
    public static final List<String> ACCOUNT_PAGE_HEADERS_LIST = Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter");
    public static final String SHOPPING_CART_PAGE_TITLE = "Shopping Cart";
    public static final String REGISTRATION_SUCCESS_MESSAGE = "Your Account Has Been Created!";
}

