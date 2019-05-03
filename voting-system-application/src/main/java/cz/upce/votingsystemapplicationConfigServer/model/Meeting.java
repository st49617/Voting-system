package cz.upce.votingsystemapplicationConfigServer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private Timestamp start;

    public Meeting() {}

    public Meeting(Timestamp start) {
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
}
