package cz.upce.votingsystemapplication.dto;

import cz.upce.votingsystemapplication.model.Meeting;
import cz.upce.votingsystemapplication.model.Suggestion;

public class TagDto {
    private long id;
    private String name;
    private String color;
    private Suggestion suggestion;

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
}
