package cz.upce.votingsystemapplication.service;

import cz.upce.votingsystemapplication.dao.MeetingDAO;
import cz.upce.votingsystemapplication.model.Meeting;
import cz.upce.votingsystemapplication.model.Suggestion;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MeetingService {

    private MeetingDAO meetingDAO;

    public MeetingService(MeetingDAO meetingDAO) {
        this.meetingDAO = meetingDAO;
    }

    public void add(Meeting meeting) {
        meetingDAO.save(meeting);
    }

    public Meeting findById(long id) {
        if (meetingDAO.findById(id).isPresent()) {
            return meetingDAO.findById(id).get();
        } else {
            throw new NoSuchElementException("No such meeting.");
        }
    }

    public List<Meeting> findAll() {
        return meetingDAO.findAll();
    }
}
