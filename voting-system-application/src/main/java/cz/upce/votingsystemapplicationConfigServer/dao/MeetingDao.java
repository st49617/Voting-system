package cz.upce.votingsystemapplicationConfigServer.dao;

import cz.upce.votingsystemapplicationConfigServer.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingDao extends JpaRepository<Meeting, Long> {
}
