package cz.upce.votingsystemapplication.testsgui.helpers.MeetingStuff;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Meeting {

    @NotNull
    private Timestamp start;

    public Meeting() {}

    public Meeting(Timestamp start) {
        this.start = start;
    }

    public Timestamp getStart() {
        return start;
    }
}
