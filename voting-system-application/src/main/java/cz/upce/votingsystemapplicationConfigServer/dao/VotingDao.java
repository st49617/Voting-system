package cz.upce.votingsystemapplicationConfigServer.dao;

import cz.upce.votingsystemapplicationConfigServer.model.Voting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VotingDao extends JpaRepository<Voting, Long> {
    public List<Voting> getAllByUserId(Long userId);

    public List<Voting> getAllBySuggestionId(Long suggestionId);
}
