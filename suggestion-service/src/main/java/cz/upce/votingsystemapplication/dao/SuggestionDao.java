package cz.upce.votingsystemapplication.dao;

import cz.upce.votingsystemapplication.model.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuggestionDao extends JpaRepository<Suggestion, Long> {

}
