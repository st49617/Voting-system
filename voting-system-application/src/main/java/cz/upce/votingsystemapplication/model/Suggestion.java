package cz.upce.votingsystemapplication.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Suggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String content;
    private Long meetingId;

    //true = accepted, false = rejected, null = not decided yet
    private Boolean accepted;

    public Suggestion() {
    }

    public Suggestion(@NotEmpty String content, @NotEmpty Long meetingId) {
        this.content = content;
        this.meetingId = meetingId;
        this.accepted = null;
    }

    public Suggestion(@NotEmpty String content, @NotEmpty Long meetingId, Boolean accepted) {
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

    public boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
