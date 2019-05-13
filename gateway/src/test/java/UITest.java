import cz.upce.votingsystemapplication.testsgui.helpers.AbstractUITest;
import org.junit.Test;
import org.openqa.selenium.By;

public class UITest extends AbstractUITest {

    @Test()
    public void InvalidLogin() {

        driver.get("file:///home/nomad/skola/inpia_sem/Voting-system/voting-system-app/dist/index.html#/login");
        driver.findElement(By.name("input-email")).sendKeys("invalid@login.com");
        driver.findElement(By.name("input-password")).sendKeys("InvalidPassword1234");
        driver.findElement(By.xpath("//div[contains(text(), \"Přihlásit\")]")).click();
        driver.findElement(By.name("input-email"));
    }

}
