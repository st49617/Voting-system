package cz.upce.votingsystemapplication.dto;

public class SuggestionForMeetingDto {

    public enum ACCEPTANCE {
        PRIJATO,
        NEPRIJATO,
        NEROZHODNUTO
    }

    private long id;
    private String content;
    private ACCEPTANCE accepted;

    public SuggestionForMeetingDto(){
    }

    public SuggestionForMeetingDto(long id, String content, ACCEPTANCE accepted) {
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

    public ACCEPTANCE getAccepted() {
        return accepted;
    }

    public void setAccepted(ACCEPTANCE accepted) {
        this.accepted = accepted;
    }
}
