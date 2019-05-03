package cz.upce.votingsystemapplicationConfigServer.dao;

import cz.upce.votingsystemapplicationConfigServer.model.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuggestionDao extends JpaRepository<Suggestion, Long> {

}
