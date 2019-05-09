package cz.upce.votingsystemapplication.dto;

import cz.upce.votingsystemapplication.model.Voting;

public class VotingDto {

    private Voting.VOTE vote;
    private SuggestionDto suggestion;
//    TODO: Dodělat až bude
//    private UserDto user;


    public VotingDto(Voting.VOTE vote, SuggestionDto suggestion) {
        this.vote = vote;
        this.suggestion = suggestion;
    }

    public Voting.VOTE getVote() {
        return vote;
    }

    public SuggestionDto getSuggestion() {
        return suggestion;
    }

    public void setVote(Voting.VOTE vote) {
        this.vote = vote;
    }

    public void setSuggestion(SuggestionDto suggestion) {
        this.suggestion = suggestion;
    }
}
