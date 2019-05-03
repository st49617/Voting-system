package cz.upce.votingsystemapplicationConfigServer.controller;

import cz.upce.votingsystemapplicationConfigServer.dto.VotingDto;
import cz.upce.votingsystemapplicationConfigServer.model.Voting;
import cz.upce.votingsystemapplicationConfigServer.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/voting")
public class VotingRestController {

    @Autowired
    VotingService votingService;

    @PostMapping("add")
    public void addVote(@RequestBody Voting voting) {
        votingService.addVoting(voting);
    }

    @GetMapping("get/{id}")
    public VotingDto getVoting(@PathVariable Long id) {
        return votingService.getVoting(id);
    }

    @GetMapping("get-all")
    public List<VotingDto> getVoting() {
        return votingService.getAllVotings();
    }

    @GetMapping("get-user/{userId}")
    public List<VotingDto> getUserVotings(@PathVariable Long userId) {
        return votingService.getUserVotings(userId);
    }

    @GetMapping("get-suggestion/{suggestionId}")
    public List<VotingDto> getSuggestionVotings(@PathVariable Long suggestionId) {
        return votingService.getSuggestionVotings(suggestionId);
    }
}
