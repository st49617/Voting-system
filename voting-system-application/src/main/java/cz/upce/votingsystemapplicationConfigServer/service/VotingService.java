package cz.upce.votingsystemapplicationConfigServer.service;

import cz.upce.votingsystemapplicationConfigServer.dao.VotingDao;
import cz.upce.votingsystemapplicationConfigServer.dto.SuggestionDto;
import cz.upce.votingsystemapplicationConfigServer.dto.VotingDto;
import cz.upce.votingsystemapplicationConfigServer.model.Voting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VotingService {

    @Autowired
    private VotingDao votingDao;
    @Autowired
    private SuggestionService suggestionService;

    public void addVoting(Long userId, Long suggestionId, Voting.VOTE vote) {
        Voting voting = new Voting(userId, suggestionId, vote);
        votingDao.save(voting);
    }

    public void addVoting(Voting voting) {
        votingDao.save(voting);
    }

    public VotingDto getVoting(Long id) {
        if (votingDao.findById(id).isPresent()) {
            Voting voting = votingDao.findById(id).get();
            return mapVotingToDto(voting);
        }
        return null;
    }

    public List<VotingDto> getAllVotings() {
        return mapVotingToDtoList(votingDao.findAll());
    }

    public List<VotingDto> getUserVotings(Long userId) {
        return mapVotingToDtoList(votingDao.getAllByUserId(userId));
    }

    public List<VotingDto> getSuggestionVotings(Long suggestionId) {
        return mapVotingToDtoList(votingDao.getAllBySuggestionId(suggestionId));
    }

    private VotingDto mapVotingToDto(Voting voting) {
        SuggestionDto suggestion = suggestionService.findById(voting.getSuggestionId());
        VotingDto votingDto = new VotingDto(voting.getVote(), suggestion);
        return votingDto;
    }

    private List<VotingDto> mapVotingToDtoList(List<Voting> votings) {
        List<VotingDto> votingDtoList = new ArrayList<>();
        votings.forEach(voting -> votingDtoList.add(mapVotingToDto(voting)));
        return votingDtoList;
    }


}
