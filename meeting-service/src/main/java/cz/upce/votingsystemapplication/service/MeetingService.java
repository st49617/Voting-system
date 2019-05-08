package cz.upce.votingsystemapplication.service;

import cz.upce.votingsystemapplication.client.SuggestionClient;
import cz.upce.votingsystemapplication.dao.MeetingDao;
import cz.upce.votingsystemapplication.dto.MeetingDto;
import cz.upce.votingsystemapplication.dto.MeetingForSuggestionDto;
import cz.upce.votingsystemapplication.dto.SuggestionForMeetingDto;
import cz.upce.votingsystemapplication.model.Meeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MeetingService {

    @Autowired
    private MeetingDao meetingDAO;

    @Autowired
    private SuggestionClient suggestionClient;

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
        suggestions = suggestionClient.getSuggestionsOnMeeting(meeting.getId());

        return new MeetingDto(meeting.getId(), meeting.getStart(), suggestions);
    }

    private MeetingForSuggestionDto mapMeetingToDtoForSuggestion(Meeting meeting) {
        return new MeetingForSuggestionDto(meeting.getId(), meeting.getStart());
    }
}
