package cz.upce.votingsystemapplication.service;

import cz.upce.votingsystemapplication.dao.MeetingDAO;
import cz.upce.votingsystemapplication.entity.Meeting;
import cz.upce.votingsystemapplication.entity.Suggestion;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class MeetingService {

    private MeetingDAO meetingDAO;
    private List<Suggestion> suggestionList;

    {
        suggestionList = new LinkedList<>();
    }

    public MeetingService(MeetingDAO meetingDAO) {
        this.meetingDAO = meetingDAO;
    }

    public void add(Date date) {
        meetingDAO.save(new Meeting(date));
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
