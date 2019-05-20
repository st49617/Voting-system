import cz.upce.votingsystemapplication.testsgui.helpers.AbstractUITest;
import cz.upce.votingsystemapplication.testsgui.helpers.MeetingStuff.Meeting;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public class UITest extends AbstractUITest {

    @Test
    public void InvalidLogin() throws URISyntaxException {
        URI webPage = this.getClass().getResource("index.html").toURI();

        driver.get(webPage + "#/login");
        driver.findElement(By.name("input-email")).sendKeys("invalid@login.com");
        driver.findElement(By.name("input-password")).sendKeys("InvalidPassword1234");
        driver.findElement(By.xpath("//div[contains(text(), \"Přihlásit\")]")).click();
        driver.findElement(By.name("input-email"));
    }

    @Test
    public void addSuggestion() throws URISyntaxException {
        URI webPage = this.getClass().getResource("index.html").toURI();

        try {
            createTestMeeting();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        double randomNumber = new Random().nextDouble();
        String testText = "Testovaci selenium suggestion " + randomNumber;

        driver.get(webPage.toString() + "#/suggestion");
        driver.findElement(By.name("content")).sendKeys(testText);
        driver.findElement(By.className("v-select__selections")).click();

        // Explicitni cekani na nacteni meeting listu.
        new WebDriverWait(driver, 10).until(ExpectedConditions.textMatches(By.className("v-list__tile__title"), Pattern.compile("[0-9]{1,2}\\.[0-9]{1,2}\\. [0-9]{4}.*")));
        List<WebElement> listElements = driver.findElements(By.className("v-list__tile__title"));
        String meeting = listElements.get(listElements.size() - 1).getText();
        listElements.get(listElements.size() - 1).click();

        driver.findElement(By.xpath("//div[contains(text(), \"Uložit\")]")).click();

        //todo nahradit správnou cestou
        driver.get(webPage.toString() + "#/meeting");

        // Explicitni cekani na nacteni meeting listu.
        new WebDriverWait(driver, 10).until(ExpectedConditions.textMatches(By.className("v-select__selections"), Pattern.compile("[0-9]{1,2}\\.[0-9]{1,2}\\. [0-9]{4}.*")));
        // Overi, ze se nacetl onen pridany meeting.
        Assert.assertEquals(meeting, driver.findElement(By.className("v-select__selection")).getText());

        //xPath cesta do tabulky
        String xPathToRows = "/html/body/div/div[2]/div/div/div/div/div[2]/div/div/div/div/table/tbody/tr/td[1]";

        new WebDriverWait(driver, 10).until(
                webDriver -> driver.findElements(By.xpath(xPathToRows)).stream().anyMatch(webElement -> webElement.getText().compareTo(testText) == 0)
        );

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