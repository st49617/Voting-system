package cz.upce.votingsystemapplication.client;

import cz.upce.votingsystemapplication.dto.MeetingDto;
import cz.upce.votingsystemapplication.dto.MeetingForSuggestionDto;
import cz.upce.votingsystemapplication.dto.SuggestionForMeetingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "meeting-service", fallback = MeetingClientFallback.class)
public interface MeetingClient {

    @RequestMapping(method = RequestMethod.GET, value = "get/{id}")
    MeetingDto get(@PathVariable("id") Long id);

    @RequestMapping(method = RequestMethod.GET, value = "get-all")
    List<MeetingDto> getAll();

    @RequestMapping(method = RequestMethod.POST, value = "add")
    String add(@RequestBody MeetingDto meeting);
}