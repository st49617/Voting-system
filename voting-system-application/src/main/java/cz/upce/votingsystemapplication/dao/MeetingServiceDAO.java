package cz.upce.votingsystemapplication.dao;

import cz.upce.votingsystemapplication.entity.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingServiceDAO extends JpaRepository<Suggestion, Long> {
}
