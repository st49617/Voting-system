package cz.upce.votingsystemapplication.service;

import cz.upce.votingsystemapplication.dao.MeetingDao;
import cz.upce.votingsystemapplication.dto.MeetingDto;
import cz.upce.votingsystemapplication.dto.SuggestionDto;
import cz.upce.votingsystemapplication.model.Meeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MeetingService {

    private MeetingDao meetingDAO;
    private SuggestionService suggestionService;

    @Autowired
    public MeetingService(MeetingDao meetingDAO, SuggestionService suggestionService) {
        this.meetingDAO = meetingDAO;
        this.suggestionService = suggestionService;
    }

    public void add(Meeting meeting) {
        meetingDAO.save(meeting);
    }

    public MeetingDto findById(long id) {
        if (meetingDAO.findById(id).isPresent()) {
            return mapMeetingToDto(meetingDAO.findById(id).get());
        } else {
            throw new NoSuchElementException("No such meeting.");
        }
    }

    public List<MeetingDto> findAll() {
        List<MeetingDto> meetings = new LinkedList<>();
        meetingDAO
                .findAll()
                .forEach(meeting -> meetings
                        .add(mapMeetingToDto(meeting)));

        return meetings;
    }

    public void deleteById(long id) {
        if (meetingDAO.findById(id).isPresent()) {
            meetingDAO.deleteById(id);
        } else {
            throw new NoSuchElementException("No such meeting.");
        }
    }

    private MeetingDto mapMeetingToDto(Meeting meeting) {
        List<SuggestionDto> suggestions;
        try {
            suggestions = new LinkedList<>(suggestionService.findAllSuggestionsOnMeeting(meeting.getId()));
        } catch (NullPointerException npex) {
            suggestions = new LinkedList<>();
        }

        return new MeetingDto(meeting.getId(), meeting.getStart(), suggestions);
    }
}
