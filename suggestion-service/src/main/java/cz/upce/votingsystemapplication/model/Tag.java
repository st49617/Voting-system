package cz.upce.votingsystemapplication.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;

    @NotNull
    private String color;


    @ManyToMany
    @JoinColumn(name="suggestionId", nullable=true, updatable=false)
    private List<Suggestion> suggestion;//body jednani

    public Tag() {
    }

    public Tag(@NotNull String name, @NotNull String color, List<Suggestion> suggestion) {
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

    public List<Suggestion> getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(List<Suggestion> suggestion) {
        this.suggestion = suggestion;
    }

    public void addSuggestion(Suggestion suggestion) {
        this.suggestion.add(suggestion);
    }

    public void removeSuggestion(Suggestion suggestion) {
        this.suggestion.removeIf(selectedSuggestion -> selectedSuggestion.getId() == suggestion.getId());
    }


    //TODO štítek

}
