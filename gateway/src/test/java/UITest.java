import cz.upce.votingsystemapplication.testsgui.helpers.AbstractUITest;
import cz.upce.votingsystemapplication.testsgui.helpers.MeetingStuff.Meeting;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public class UITest extends AbstractUITest {

    //TODO PO KONTROLE A PRESUNU SMAZAT POPISNY CESKY KOMENTY

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
        try {
            createTestMeeting();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        double randomNumber = new Random().nextDouble();
        String testText = "Testovaci selenium suggestion " + randomNumber;

        //todo nahradit správnou cestou
        driver.get("C:\\Users\\user\\Documents\\Škola\\INPIA\\voting\\Voting-system\\voting-system-app\\dist#/suggestion");

        //Vyplní text návrhu
        driver.findElement(By.name("content")).sendKeys(testText);

        //Vybere jeden existujici meeting
        List<WebElement> listElements = driver.findElements(By.className("v-list__tile__content"));
        listElements.get(0).click();

        //klikne na uložit
        driver.findElement(By.xpath("//div[contains(text(), \"Uložit\")]")).click();

        //todo nahradit správnou cestou k meetingům kde jsou vypsaný i suggestiony
        driver.get("C:\\Users\\user\\Documents\\Škola\\INPIA\\voting\\Voting-system\\voting-system-app\\dist#/meeting");

        //Vybere jeden z existujici meeting
        List<WebElement> listElements2 = driver.findElements(By.className("v-list__tile__content"));
        listElements2.get(0).click();

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

    private void createTestMeeting() throws URISyntaxException {
        Meeting meeting = new Meeting(Timestamp.from(Instant.now()));
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:8089/api/meeting/add";
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<Meeting> request = new HttpEntity<>(meeting, headers);

        restTemplate.postForEntity(uri, request, String.class);
    }
}
