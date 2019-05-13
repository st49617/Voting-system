package cz.upce.votingsystemapplication.testsgui.helpers;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static cz.upce.votingsystemapplication.testsgui.helpers.DriverHelper.getWebDriver;


public class AbstractUITest {
    protected static WebDriver driver;

    @BeforeClass
    public static void openBrowser(){
        driver = getWebDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void closeBrowser(){
        driver.quit();
    }

}
