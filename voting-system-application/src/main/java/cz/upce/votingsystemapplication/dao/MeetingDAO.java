package cz.upce.votingsystemapplication.dao;

import cz.upce.votingsystemapplication.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingDAO extends JpaRepository<Meeting, Long> {
}
