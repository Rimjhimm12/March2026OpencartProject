package com.qa.opencart.factory;
import com.qa.opencart.constants.ConstantsUtil;
import com.qa.opencart.errors.AppErrors;
import com.qa.opencart.exceptions.BrowserException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class DriverFactory {
     WebDriver driver;
     Properties prop;
     OptionsManager optionsManager;
     public static String highlight;
     public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();


    /**
     * This method is used to initialize the driver on the basis of given browser name and url
     * @param properties - Properties object containing the key-value pairs from config.properties file,
     *             which includes the browser name and url
     *
     */
    public  WebDriver initDriver(Properties properties){
        highlight = properties.getProperty("highlight");
        optionsManager = new OptionsManager(properties);

        switch (properties.getProperty("browser").toLowerCase().trim()){
            case "chrome":
                //driver = new ChromeDriver();
                if(Boolean.parseBoolean(prop.getProperty("remote"))){
                    initRemoteDriver("chrome");
                }
                else {
                    tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
                }
                break;

            case "firefox":
                //driver = new FirefoxDriver();
                if(Boolean.parseBoolean(prop.getProperty("remote"))){
                    initRemoteDriver("firefox");
                }
                else {
                    tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
                }
                break;
            case "safari":
                //driver = new SafariDriver();
                tlDriver.set(new SafariDriver());
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", "/Users/rimjhim/Desktop/edgedriver_mac64/msedgedriver");
                //driver = new EdgeDriver();
                if(Boolean.parseBoolean(prop.getProperty("remote"))){
                    initRemoteDriver("edge");
                }
                else {
                    tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
                }
                break;
            default:
                System.out.println("Please pass the correct browser; e.g: chrome, firefox, edge or safari");
                throw new BrowserException(AppErrors.BROWSER_NOT_FOUND);
        }
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        getDriver().get(properties.getProperty("url"));
        return getDriver();
    }

    /**
    * *
     * This method is used to get the driver instance using ThreadLocal
     *
     */

    public static WebDriver getDriver(){
        return tlDriver.get();
    }

    /**
     * This method is used to initialize the properties from config.properties file
     * @return prop - Properties object containing the key-value pairs from config.properties file
     */

    public Properties initProp() {

        //mvn clean install -Denv="qa"
        String envName = System.getProperty("env");

        prop = new Properties();
        FileInputStream ip = null;

        if (envName == null) {
            System.out.println("Running on default environment: config properties");
            try {
                ip = new FileInputStream(ConstantsUtil.CONFIG_FILE_PATH);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            try {
                switch (envName.toLowerCase().trim()) {

                    case "qa":
                        ip = new FileInputStream(ConstantsUtil.QA_FILE_PATH);
                        break;
                    case "dev":
                        ip = new FileInputStream(ConstantsUtil.DEV_FILE_PATH);
                        break;
                    case "stage":
                        ip = new FileInputStream(ConstantsUtil.STAGE_FILE_PATH);
                        break;
                    case "uat":
                        ip = new FileInputStream(ConstantsUtil.UAT_FILE_PATH);
                        break;
                    default:
                        System.out.println("Please pass the correct environment name: qa, dev, stage or uat");
                        throw new BrowserException(AppErrors.ENVIRONMENT_NOT_FOUND);

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        try {
            prop.load(ip);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return prop;
    }

    /***
     * This method is used to initialize the remote driver for Selenium Grid execution
     */

    public void initRemoteDriver(String browserName){
        System.out.println("Rnning it on GRID...with browser: " + browserName);
        try {
            switch (browserName) {
                case "chrome":
                    tlDriver.set (
                           new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions())) ;
                    break;
                case "firefox":
                    tlDriver.set(
                            new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFirefoxOptions())) ;
                    break;
                case "edge":
                tlDriver.set(
                        new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOptions()));
                break;

                default:
                    System.out.println("plz pass the right browser on grid..");
                    throw new BrowserException(AppErrors.BROWSER_NOT_FOUND) ;
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }



    /***
     * take screenshort
     */

    public static String getScreenshot(String methodName){
        File tempFileLocation = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        Path screenshotDirectory = Paths.get(System.getProperty("user.dir"), "screenshots");
        Path destinationPath = screenshotDirectory.resolve(methodName + ".jpg");
        File destination = destinationPath.toFile();
        try {
            Files.createDirectories(screenshotDirectory);
            FileHandler.copy(tempFileLocation,destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "../screenshots/" + methodName + ".jpg";
    }




}
