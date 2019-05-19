import cz.upce.votingsystemapplication.testsgui.helpers.AbstractUITest;
import cz.upce.votingsystemapplication.testsgui.helpers.MeetingStuff.Meeting;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;

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
    public void addSuggestion() throws InterruptedException {
        try {
            createTestMeeting();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        double randomNumber = new Random().nextDouble();
        String testText = "Testovaci selenium suggestion " + randomNumber;

        //todo nahradit správnou cestou
        driver.get("file:///home/nomad/skola/inpia_sem/Voting-system/voting-system-app/dist/index.html#/suggestion");
        //driver.get("C:\\Users\\user\\Documents\\Škola\\INPIA\\voting\\Voting-system\\voting-system-app\\dist#/suggestion");

        //Vyplní text návrhu
        driver.findElement(By.name("content")).sendKeys(testText);

        //Vybere jeden existujici meeting
        driver.findElement(By.className("v-select__selections")).click();
        List<WebElement> listElements = driver.findElements(By.className("v-list__tile__title"));
        String meeting = listElements.get(listElements.size() - 1).getText();
        listElements.get(listElements.size() - 1).click();

        //klikne na uložit
        driver.findElement(By.xpath("//div[contains(text(), \"Uložit\")]")).click();

        //todo nahradit správnou cestou k meetingům kde jsou vypsaný i suggestiony
        driver.get("file:///home/nomad/skola/inpia_sem/Voting-system/voting-system-app/dist/index.html#/meeting");
        //driver.get("C:\\Users\\user\\Documents\\Škola\\INPIA\\voting\\Voting-system\\voting-system-app\\dist#/meeting");

        // NEFUNGUJE, vybere posledni zrovna vylistovany meeting (nescrollne).
        //Vybere jeden z existujici meeting
//        driver.findElement(By.className("v-select__selections")).click();
        //new WebDriverWait(driver, 10).until(ExpectedConditions.textToBe(By.className("v-list__tile__title"), meeting));

        // NEFUNGUJE, prochazi pouze zrovna vylistovane meetingy (nescrollne).
        // NEFUNGUJE, scroll z neznamych duvodu scrollne jen jednou a to o jediny zaznam,
        // presto ze by mel scrollnout celkem o 20*5000 px.
//        JavascriptExecutor javascriptExecutor = (JavascriptExecutor)driver;
//        new WebDriverWait(driver, 60)
//                .until(webDriver -> {
//                    javascriptExecutor.executeScript("scroll(0, 5000);");
//                    return driver.findElements(By.className("v-list__tile__title"))
//                                    .stream()
//                                    .anyMatch(webElement -> webElement.getText().compareTo(meeting) == 0);
//                        });

//        List<WebElement> listElements2 = driver.findElements(By.className("v-list__tile__title"));

        // NEFUNGUJE, prochazi pouze zrovna vylistovane meetingy (nescrollne)
        //List<WebElement> listElements2 = driver.findElements(By.linkText(meeting));
        //listElements2.get(listElements.size() - 1).click();
//        listElements2.stream().filter(webElement -> webElement.getText().compareTo(meeting) == 0).findFirst().get().click();
                //(listElements.size() - 1).click();



        //xPath cesta do tabulky, je to dost WTF, ale takhle zrejme vypada, protoze elementy nemaji IDcka pro zjednoduseni
        String xPathToRows = "/html/body/div/div[2]/div/div/div/div/div[2]/div/div/div/div/table/tbody/tr/td[1]";

        new WebDriverWait(driver, 10).until(
                webDriver -> driver.findElements(By.xpath(xPathToRows)).stream().anyMatch(webElement -> {
                    System.out.println(testText + " | " + webElement.getText() );
                    return webElement.getText().compareTo(testText) == 0;
                })
        );

        //dostanu vechny zaznamy tabulky a v nich hledam
        List<WebElement> rows = driver.findElements(By.xpath(xPathToRows));

        boolean exists = false;
        for (WebElement row : rows) {
            if (row.getText().equals(testText)) {
                exists = true;
            }
        }
        Assert.assertTrue(exists);
    }

    private void createTestMeeting() throws URISyntaxException {
        Meeting meeting = new Meeting(Timestamp.from(Instant.now()));
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:8079/add";
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<Meeting> request = new HttpEntity<>(meeting, headers);

        restTemplate.postForEntity(uri, request, String.class);
    }
}
