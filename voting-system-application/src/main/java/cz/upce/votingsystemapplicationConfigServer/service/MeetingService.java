package cz.upce.votingsystemapplicationConfigServer.service;

import cz.upce.votingsystemapplicationConfigServer.dao.MeetingDao;
import cz.upce.votingsystemapplicationConfigServer.dto.MeetingDto;
import cz.upce.votingsystemapplicationConfigServer.dto.MeetingForSuggestionDto;
import cz.upce.votingsystemapplicationConfigServer.dto.SuggestionForMeetingDto;
import cz.upce.votingsystemapplicationConfigServer.model.Meeting;
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
    public MeetingService(MeetingDao meetingDAO) {
        this.meetingDAO = meetingDAO;
    }

    // Setter-based DI - kvuli Spring ochrane proti cyklickym zavislostem. V konstruktoru nelze -> nemenit!
    @Autowired
    public void setSuggestionService(SuggestionService suggestionService) {
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

    public MeetingForSuggestionDto findByIdForSuggestion(long id) {
        if (meetingDAO.findById(id).isPresent()) {
            return mapMeetingToDtoForSuggestion(meetingDAO.findById(id).get());
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
        List<SuggestionForMeetingDto> suggestions;
        try {
            suggestions = new LinkedList<>(suggestionService.findAllSuggestionsOnMeeting(meeting.getId()));
        } catch (NullPointerException npex) {
            suggestions = new LinkedList<>();
        }

        return new MeetingDto(meeting.getId(), meeting.getStart(), suggestions);
    }

    private MeetingForSuggestionDto mapMeetingToDtoForSuggestion(Meeting meeting) {
        return new MeetingForSuggestionDto(meeting.getId(), meeting.getStart());
    }
}
