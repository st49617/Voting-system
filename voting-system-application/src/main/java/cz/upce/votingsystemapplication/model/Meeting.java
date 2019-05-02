package cz.upce.votingsystemapplication.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Entity
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private Timestamp start;

    @OneToMany(mappedBy = "id")
    private List<Suggestion> suggestions;

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

    public List<Suggestion> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<Suggestion> suggestions) {
        this.suggestions = suggestions;
    }
}
