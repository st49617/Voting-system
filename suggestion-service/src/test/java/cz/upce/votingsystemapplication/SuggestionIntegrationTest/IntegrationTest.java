package cz.upce.votingsystemapplication.SuggestionIntegrationTest;

import cz.upce.votingsystemapplication.client.MeetingClient;
import cz.upce.votingsystemapplication.dto.MeetingDto;
import java.sql.Timestamp;
import java.time.Instant;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTest {

  @Autowired
  private MeetingClient meetingClient;

  @Test
  public void integrationTestFeignClient(){
    MeetingDto meetingDto = new MeetingDto();
    meetingDto.setSuggestions(null);
    meetingDto.setId(-1000L);
    meetingDto.setStart(Timestamp.from(Instant.now()));
    meetingClient.add(meetingDto);
    Assert.assertNotNull(meetingClient.get(-100L));
    }
}