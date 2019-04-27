package cz.upce.votingsystemapplication.service;

import cz.upce.votingsystemapplication.dao.MeetingDAO;
import cz.upce.votingsystemapplication.model.Meeting;
import cz.upce.votingsystemapplication.model.Suggestion;
import org.springframework.stereotype.Service;

import javax.naming.CannotProceedException;
import javax.transaction.Transactional;
import java.text.ParseException;
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

    public void add(String start) throws CannotProceedException, ParseException {
        Pattern tagRegex = Pattern.compile("^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])-(0[0-9]|1[0-9]|2[0-3]|[0-9])-[0-5][0-9])$");
        Matcher matcher = tagRegex.matcher(start);

        if (matcher.matches())
            meetingDAO.save(new Meeting(start));
        else
            throw new CannotProceedException("Format of start should be YYYY-MM-DD-hh-mm");
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
