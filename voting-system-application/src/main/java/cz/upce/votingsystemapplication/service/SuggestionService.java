package cz.upce.votingsystemapplication.service;

import cz.upce.votingsystemapplication.dao.MeetingDAO;
import cz.upce.votingsystemapplication.dao.SuggestionDao;
import cz.upce.votingsystemapplication.model.Meeting;
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
  private MeetingDAO meetingDao;
  private final static Logger LOGGER = Logger.getLogger(SuggestionService.class.getName());

  @Autowired
  public SuggestionService(SuggestionDao suggestionDao, MeetingDAO meetingDao) {
    this.meetingDao = meetingDao;
    this.suggestionDao = suggestionDao;
  }

  public List<Suggestion> findAll() {
    return suggestionDao.findAll();
  }

  public Suggestion findById(Long id) {
    if (suggestionDao.findById(id).isPresent()) {
      return suggestionDao.findById(id).get();
    } else {
      //TODO i think logging a message and returning null value is better than throwing an exception
      LOGGER.log(Level.WARNING, String.format("Suggestion with id %d does not exist in the database.", id));
      return null;
    }
  }

  public List<Suggestion> findAllSuggestionsOnMeeting(Long id){
    List<Suggestion> all = suggestionDao.findAll();
    List<Suggestion> filtered = new ArrayList<>();
    Meeting meeting = null;
    try{
      meeting = meetingDao.getOne(id);
    } catch (Exception e) {
      LOGGER.log(Level.WARNING, String.format("Meeting with id %d does not exist in the database."
          + " So there are no suggestions for this meeting", id));
    }
    for(Suggestion suggestion : all) {
      if(meeting != null && suggestion.getMeeting() == meeting) {
        filtered.add(suggestion);
      }
    }
    return filtered;
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
}
