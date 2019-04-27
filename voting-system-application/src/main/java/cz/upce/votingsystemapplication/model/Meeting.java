package cz.upce.votingsystemapplication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Entity
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private Timestamp start;

    public Meeting() {}

    public Meeting(@NotEmpty String start) throws ParseException {
        this.setStart(start);
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

    public void setStart(String start) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
        java.util.Date javaStart = format.parse(start);

        this.start = new Timestamp(javaStart.getTime());
    }
}
