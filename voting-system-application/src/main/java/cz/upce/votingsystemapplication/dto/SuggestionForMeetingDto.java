package cz.upce.votingsystemapplication.dto;

public class SuggestionForMeetingDto {

    private long id;
    private String content;
    private Boolean accepted;

    public SuggestionForMeetingDto(){
    }

    public SuggestionForMeetingDto(long id, String content, Boolean accepted) {
        this.id = id;
        this.content = content;
        this.accepted = accepted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }
}
