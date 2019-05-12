package cz.upce.votingsystemapplication.client;

import cz.upce.votingsystemapplication.dto.SuggestionForMeetingDto;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class SuggestionClientFallback implements SuggestionClient {

    @Override
    public List<SuggestionForMeetingDto> getSuggestionsOnMeeting(Long id) {
        return new LinkedList<>();
    }
}
