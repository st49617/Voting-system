package cz.upce.votingsystemapplication.service;

import cz.upce.votingsystemapplication.dao.MeetingDao;
import cz.upce.votingsystemapplication.dao.SuggestionDao;
import cz.upce.votingsystemapplication.dto.SuggestionDto;
import cz.upce.votingsystemapplication.model.Suggestion;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuggestionService {

  private SuggestionDao suggestionDao;
  private MeetingDao meetingDao;
  private final static Logger LOGGER = Logger.getLogger(SuggestionService.class.getName());

  @Autowired
  public SuggestionService(SuggestionDao suggestionDao, MeetingDao meetingDao) {
    this.meetingDao = meetingDao;
    this.suggestionDao = suggestionDao;
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

  public List<SuggestionDto> findAllSuggestionsOnMeeting(Long id){

    List<Suggestion> all = suggestionDao.findAll();
    List<SuggestionDto> dtoOutList = new ArrayList<>();

    all.removeIf(suggestion -> !suggestion.getMeetingId().equals(id));
    if(all.isEmpty()) {
      LOGGER.log(Level.WARNING, String.format("Meeting with id %d does not exist in the database."
          + " So there are no suggestions for this meeting", id));
      return null;
    } else {
      all.forEach(suggestion -> {
        dtoOutList.add(mapSuggestionToDto(suggestion));
      });
    }
    return dtoOutList;
  }

  public void add(Suggestion suggestion) {
    suggestionDao.save(suggestion);
  }

  public void deleteById(Long id) {
    try{
      suggestionDao.deleteById(id);
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, String.format("Suggestion with id %d could not be deleted.", id));
    }
  }

  public void delete(Suggestion suggestion) {
    try{
      suggestionDao.delete(suggestion);
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, String.format("Suggestion with id %d could not be deleted.", suggestion.getId()));
    }
  }

  public void markAsAccepted(Long suggestionId) {
    markAcceptance(suggestionId, true);
  }

  public void markAsRejected(Long suggestionId) {
    markAcceptance(suggestionId, false);
  }

  private void markAcceptance(Long suggestionId, boolean b) {
    Suggestion suggestion = suggestionDao.getOne(suggestionId);
    suggestion.setAccepted(b);
    suggestionDao.save(suggestion);
  }

  private SuggestionDto mapSuggestionToDto(Suggestion suggestion) {
    SuggestionDto dtoOut = new SuggestionDto();
    dtoOut.setId(suggestion.getId());
    dtoOut.setContent(suggestion.getContent());
    dtoOut.setAccepted(suggestion.getAccepted());

    //FIXME just temporarily using dao
    dtoOut.setMeeting(meetingDao.getOne(suggestion.getMeetingId()));
    return dtoOut;
  }
}
