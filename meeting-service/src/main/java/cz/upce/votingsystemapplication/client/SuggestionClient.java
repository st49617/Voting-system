package cz.upce.votingsystemapplication.client;

import cz.upce.votingsystemapplication.dto.SuggestionForMeetingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "voting-system-application", fallback = SuggestionClientFallback.class)
public interface SuggestionClient {

    @RequestMapping(method = RequestMethod.GET, value = "api/suggestion/get/meeting/{id}")
    List<SuggestionForMeetingDto> getSuggestionsOnMeeting(@PathVariable("id") Long id);
}