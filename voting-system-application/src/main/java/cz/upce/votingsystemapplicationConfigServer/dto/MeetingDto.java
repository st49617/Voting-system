package cz.upce.votingsystemapplicationConfigServer.dto;

import java.sql.Timestamp;
import java.util.List;

public class MeetingDto extends MeetingForSuggestionDto {

    private List<SuggestionForMeetingDto> suggestions;

    public MeetingDto(long id, Timestamp start, List<SuggestionForMeetingDto> suggestions) {
        super(id, start);

        this.suggestions = suggestions;
    }

    public List<SuggestionForMeetingDto> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<SuggestionForMeetingDto> suggestions) {
        this.suggestions = suggestions;
    }
}