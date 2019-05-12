package cz.upce.votingsystemapplication.service;

import cz.upce.votingsystemapplication.client.MeetingClient;
import cz.upce.votingsystemapplication.dao.SuggestionDao;
import cz.upce.votingsystemapplication.dto.MeetingForSuggestionDto;
import cz.upce.votingsystemapplication.dto.SuggestionDto;
import cz.upce.votingsystemapplication.dto.SuggestionForMeetingDto;
import cz.upce.votingsystemapplication.model.Suggestion;
import cz.upce.votingsystemapplication.model.Suggestion.ACCEPTANCE;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SuggestionService {

  private SuggestionDao suggestionDao;
  private MeetingClient meetingClient;

  private final static Logger LOGGER = Logger.getLogger(SuggestionService.class.getName());

  @Autowired
  public SuggestionService(SuggestionDao suggestionDao, MeetingClient meetingClient) {
    this.suggestionDao = suggestionDao;
    this.meetingClient = meetingClient;
  }

  public List<Suggestion> findAll() {
    return suggestionDao.findAll();
  }

  public SuggestionDto findById(Long id) {

    if (suggestionDao.findById(id).isPresent()) {
      Suggestion suggestion = suggestionDao.findById(id).get();
      return mapSuggestionToDto(suggestion);
    } else {
      //i think logging a message and returning null value is better than throwing an exception
      LOGGER.log(Level.WARNING, String.format("Suggestion with id %d does not exist in the database.", id));
      return null;
    }
  }

  public List<SuggestionForMeetingDto> findAllSuggestionsOnMeeting(Long id){

    List<Suggestion> all = suggestionDao.findAll();
    List<SuggestionForMeetingDto> dtoOutList = new ArrayList<>();

    all.removeIf(suggestion -> !suggestion.getMeetingId().equals(id));
    if(all.isEmpty()) {
      LOGGER.log(Level.WARNING, String.format("Meeting with id %d does not exist in the database."
          + " So there are no suggestions for this meeting", id));
      return null;
    } else {
      all.forEach(suggestion -> {
        dtoOutList.add(mapSuggestionToDtoForMeeting(suggestion));
      });
    }
    return dtoOutList;
  }

  public Suggestion add(Suggestion suggestion) {
    return suggestionDao.save(suggestion);
  }

  public void deleteById(Long id) {
    try{
      suggestionDao.deleteById(id);
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, String.format("Suggestion with id %d could not be deleted.", id));
      throw e;
    }
  }

  public void delete(Suggestion suggestion) {
    try{
      suggestionDao.delete(suggestion);
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, String.format("Suggestion with id %d could not be deleted.", suggestion.getId()));
      throw e;
    }
  }

  //testing purposes only, very hardcore
  public void deleteAll(){
    try{
      suggestionDao.deleteAll();
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE,"All suggestions could not be deleted.");
      throw e;
    }
  }

  public void markAsAccepted(Long suggestionId) {
    markAcceptance(suggestionId, ACCEPTANCE.PRIJATO);
  }

  public void markAsRejected(Long suggestionId) {
    markAcceptance(suggestionId, ACCEPTANCE.NEPRIJATO);
  }

  private void markAcceptance(Long suggestionId, ACCEPTANCE acceptance) {
    try {
      Suggestion suggestion = suggestionDao.findById(suggestionId).get();
      suggestion.setAccepted(acceptance);
      suggestionDao.save(suggestion);
    }catch (Exception e) {
      LOGGER.log(Level.SEVERE,"Suggestion could not be saved. Marking acceptance has failed.");
      throw e;
    }
  }

  private SuggestionDto mapSuggestionToDto(Suggestion suggestion) {
    SuggestionDto dtoOut = new SuggestionDto();
    dtoOut.setId(suggestion.getId());
    dtoOut.setContent(suggestion.getContent());
    dtoOut.setAccepted(suggestion.getAccepted());

    dtoOut.setMeeting(meetingClient.get(suggestion.getMeetingId()));
    return dtoOut;
  }

  private SuggestionForMeetingDto mapSuggestionToDtoForMeeting(Suggestion suggestion) {
    SuggestionForMeetingDto dtoOut = new SuggestionForMeetingDto();
    dtoOut.setId(suggestion.getId());
    dtoOut.setContent(suggestion.getContent());
    dtoOut.setAccepted(suggestion.getAccepted());

    return dtoOut;
  }
}
