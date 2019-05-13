package cz.upce.votingsystemapplication.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Suggestion {

    public enum ACCEPTANCE {
        PRIJATO,
        NEPRIJATO,
        NEROZHODNUTO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String content;
    private Long meetingId;
    private ACCEPTANCE accepted;

    public Suggestion() {
         accepted = ACCEPTANCE.NEROZHODNUTO;
    }

    public Suggestion(@NotEmpty String content, @NotEmpty Long meetingId) {
        this.content = content;
        this.meetingId = meetingId;
        this.accepted = ACCEPTANCE.NEROZHODNUTO;
    }

    public Suggestion(@NotEmpty String content, @NotEmpty Long meetingId, ACCEPTANCE accepted) {
        this.content = content;
        this.meetingId = meetingId;
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

    public Long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
    }

    public ACCEPTANCE getAccepted() {
        return accepted;
    }

    public void setAccepted(ACCEPTANCE accepted) {
        this.accepted = accepted;
    }
}
