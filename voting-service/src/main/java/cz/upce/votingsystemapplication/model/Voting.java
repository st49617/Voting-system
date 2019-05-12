package cz.upce.votingsystemapplication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Voting {

    public enum VOTE {
        NE,
        ANO,
        ZDRZEL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private Long userId;
    @NotNull
    private Long suggestionId;

    private VOTE vote;

    public Voting() {
    }

    public Voting(@NotNull Long userId, @NotNull Long suggestionId, VOTE vote) {
        this.userId = userId;
        this.suggestionId = suggestionId;
        this.vote = vote;
    }

    public long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getSuggestionId() {
        return suggestionId;
    }

    public VOTE getVote() {
        return vote;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setSuggestionId(Long suggestionId) {
        this.suggestionId = suggestionId;
    }

    public void setVote(VOTE vote) {
        this.vote = vote;
    }
}
