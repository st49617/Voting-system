package cz.upce.votingsystemapplication.controller;

import cz.upce.votingsystemapplication.dto.VotingDto;
import cz.upce.votingsystemapplication.model.Voting;
import cz.upce.votingsystemapplication.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class VotingRestController {

    @Autowired
    VotingService votingService;

    @Value("${server.port}")
    private Integer serverPort;

    @GetMapping("try")
    public Integer tryLoadBalance() {
        return serverPort;
    }

    @PostMapping("add")
    public String addVote(@RequestBody Voting voting) {
        try {
            votingService.addVoting(voting);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "OK";
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
