package cz.upce.votingsystemapplication.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Suggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String content;
    private Long meetingId;

    //true = accepted, false = rejected, null = not decided yet
    private boolean accepted;

    @OneToMany(mappedBy = "suggestion")
    private List<Tag> tags;

    public Suggestion() {
    }

    public Suggestion(@NotEmpty String content, @NotEmpty Long meetingId) {
        this.content = content;
        this.meetingId = meetingId;
        this.accepted = true;
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
