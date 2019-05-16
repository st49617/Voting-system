import cz.upce.votingsystemapplication.testsgui.helpers.AbstractUITest;
import cz.upce.votingsystemapplication.testsgui.helpers.MeetingStuff.Meeting;
import cz.upce.votingsystemapplication.testsgui.helpers.MeetingStuff.MeetingClient;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

public class UITest extends AbstractUITest {

    //TODO PO KONTROLE A PRESUNU SMAZAT POPISNY CESKY KOMENTY

    @Autowired
    private MeetingClient meetingClient;

    @Test
    public void InvalidLogin() {
        //todo: nahradit spravnou cestou k souboru ?
        driver.get("file:///home/nomad/skola/inpia_sem/Voting-system/voting-system-app/dist/index.html#/login");
        driver.findElement(By.name("input-email")).sendKeys("invalid@login.com");
        driver.findElement(By.name("input-password")).sendKeys("InvalidPassword1234");
        driver.findElement(By.xpath("//div[contains(text(), \"Přihlásit\")]")).click();
        driver.findElement(By.name("input-email"));
    }

    @Test
    public void addSuggestion(){
        createTestMeeting();
        double randomNumber = new Random().nextDouble();
        String testText = "Testovaci selenium suggestion " + randomNumber;

        //todo nahradit správnou cestou
        driver.get("C:\\Users\\user\\Documents\\Škola\\INPIA\\voting\\Voting-system\\voting-system-app\\dist#/suggestion");

        //Vyplní text návrhu
        driver.findElement(By.name("content")).sendKeys(testText);

        //Vybere jeden z existujicich meetingů
        List<WebElement> lstElements = driver.findElements(By.className("v-list__tile__content"));
        lstElements.get(1).click();

        //klikne na uložit
        driver.findElement(By.xpath("//div[contains(text(), \"Uložit\")]")).click();

        //todo nahradit správnou cestou k meetingům kde jsou vypsaný i suggestiony
        driver.get("C:\\Users\\user\\Documents\\Škola\\INPIA\\voting\\Voting-system\\voting-system-app\\dist#/meeting");

        //xPath cesta do tabulky, je to dost WTF, ale takhle zrejme vypada, protoze elementy nemaji IDcka pro zjednoduseni
        String xPathToRows = "/html/body/div/div[2]/div/div/div/div/div[2]/div/div/div/div/table/tbody/tr/td[1]";

        //dostanu vechny zaznamy tabulky a v nich hledam
        List<WebElement> rows = driver.findElements(By.xpath(xPathToRows));

        boolean exists = false;
        for (WebElement row : rows) {
            if (row.getText().contains(testText)) {
                exists = true;
            }
        }
        Assert.assertTrue(exists);
    }

    private void createTestMeeting(){
        Meeting meeting = new Meeting(Timestamp.valueOf(LocalDateTime.now()));
        meetingClient.add(meeting);
    }
}
