package cz.upce.votingsystemapplication.service;

import cz.upce.votingsystemapplication.dao.MeetingDAO;
import cz.upce.votingsystemapplication.model.Meeting;
import cz.upce.votingsystemapplication.model.Suggestion;
import org.springframework.stereotype.Service;

import javax.naming.CannotProceedException;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public void add(String date) throws CannotProceedException {
        Pattern tagRegex = Pattern.compile("^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$");
        Matcher matcher = tagRegex.matcher(date);

        if (matcher.matches())
            meetingDAO.save(new Meeting(date));
        else
            throw new CannotProceedException("Format of the date should be yyyy-mm-dd");
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
