package cz.upce.votingsystemapplication.dao;

import cz.upce.votingsystemapplication.model.Voting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotingDao extends JpaRepository<Voting, Long> {
}
