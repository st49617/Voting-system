package cz.upce.votingsystemapplication.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

@NotNull
    private String name;

    @NotNull
    private String color;


    @NotNull
    @ManyToOne
    @JoinColumn(name="suggestionId", nullable=false, updatable=false)
    private Suggestion suggestion;//body jednani

    public Tag() {
    }

    public Tag(@NotNull String name, @NotNull String color, @NotNull Suggestion suggestion) {
        this.name = name;
        this.color = color;
        this.suggestion = suggestion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Suggestion getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(Suggestion suggestion) {
        this.suggestion = suggestion;
    }


    //TODO štítek

}
