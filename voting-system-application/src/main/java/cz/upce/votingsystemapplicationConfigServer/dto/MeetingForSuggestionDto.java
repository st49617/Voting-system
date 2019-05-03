package cz.upce.votingsystemapplicationConfigServer.dto;

import java.sql.Timestamp;

public class MeetingForSuggestionDto {

    private long id;
    private Timestamp start;

    public MeetingForSuggestionDto(long id, Timestamp start) {
        this.id = id;
        this.start = start;
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
}
