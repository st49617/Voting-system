package cz.upce.votingsystemapplication.testsgui.helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverHelper {
    private WebDriver driver;

    public DriverHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void write(By elementBy, String value) {
        driver.findElements(elementBy).get(0).sendKeys(value);
    }

    public void click(By elementBy) {
        driver.findElements(elementBy).get(0).click();
    }

    public WebElement findElementByXpath() {
        return driver.findElements(By.xpath("//ul[@class='side-nav']")).get(0);
    }

    public void assertTitle(String title) {
        Assert.assertEquals(driver.getTitle(), title);
    }

    public static WebDriver getWebDriver() {
        WebDriverManager.getInstance(FirefoxDriver.class).setup();
        return new FirefoxDriver();

    }

}
