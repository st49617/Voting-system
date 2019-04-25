package cz.upce.votingsystemapplication.controller;

import cz.upce.votingsystemapplication.model.Voting;
import cz.upce.votingsystemapplication.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/voting")
public class VotingControler {

    @Autowired
    VotingService votingService;

    @GetMapping("add")
    public void addVote(@RequestParam Long userId, @RequestParam Long suggestionId, @RequestParam Voting.VOTE vote) {
        votingService.addVoting(userId, suggestionId, vote);
    }

    @GetMapping("get/user")
    public List<Voting> getUserVotings(@RequestParam Long userId) {
        return votingService.getUserVotings(userId);
    }

    @GetMapping("get/suggestion")
    public List<Voting> getSuggestionVotings(@RequestParam Long suggestionId) {
        return votingService.getSuggestionVotings(suggestionId);
    }
}
