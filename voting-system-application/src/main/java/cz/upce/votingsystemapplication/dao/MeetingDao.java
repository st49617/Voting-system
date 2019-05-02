package cz.upce.votingsystemapplication.dao;

import cz.upce.votingsystemapplication.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingDao extends JpaRepository<Meeting, Long> {
}
