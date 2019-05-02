package cz.upce.votingsystemapplication.dto;

import java.sql.Timestamp;
import java.util.List;

public class MeetingDto {

    private long id;
    private Timestamp start;
    private List<SuggestionDto> suggestions;

    public MeetingDto(long id, Timestamp start, List<SuggestionDto> suggestions) {
        this.id = id;
        this.start = start;
        this.suggestions = suggestions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public List<SuggestionDto> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<SuggestionDto> suggestions) {
        this.suggestions = suggestions;
    }
}
