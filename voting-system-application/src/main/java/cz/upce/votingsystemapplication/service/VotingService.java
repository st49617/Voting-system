package cz.upce.votingsystemapplication.service;

import cz.upce.votingsystemapplication.dao.VotingDao;
import cz.upce.votingsystemapplication.model.Voting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class VotingService {

    @Autowired
    private VotingDao votingDao;

    public void addVoting(Long userId, Long suggestionId, Voting.VOTE vote) {
//        TODO:Vyřešit slolžený primární klíč a  při shodě jen měnit hodnotu hlasování
        Voting voting = new Voting(userId, suggestionId, vote);
        votingDao.save(voting);
    }

    public List<Voting> getUserVotings(Long userId) {
//        TODO:Refactor
        List<Voting> votingList = votingDao.findAll();

        votingList.removeIf(voting -> {
            return voting.getUserId() != userId;
        });

        return votingList;
    }

    public List<Voting> getSuggestionVotings(Long suggestionId) {
//        TODO:Refactor
        List<Voting> votingList = votingDao.findAll();

        votingList.removeIf(voting -> {
            return voting.getSuggestionId() != suggestionId;
        });

        return votingList;
    }

}
