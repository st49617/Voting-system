package cz.upce.votingsystemapplication.dto;

import cz.upce.votingsystemapplication.model.Voting;

public class VotingDto {

    private Voting.VOTE vote;
    private SuggestionDto suggestion;
    private UserDto user;


    public VotingDto(Voting.VOTE vote, SuggestionDto suggestion, UserDto user) {
        this.vote = vote;
        this.suggestion = suggestion;
        this.user = user;
    }

    public Voting.VOTE getVote() {
        return vote;
    }

    public SuggestionDto getSuggestion() {
        return suggestion;
    }

    public UserDto getUser() {
        return user;
    }

    public void setVote(Voting.VOTE vote) {
        this.vote = vote;
    }

    public void setSuggestion(SuggestionDto suggestion) {
        this.suggestion = suggestion;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
