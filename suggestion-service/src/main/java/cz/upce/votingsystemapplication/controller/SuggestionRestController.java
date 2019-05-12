package cz.upce.votingsystemapplication.controller;

import cz.upce.votingsystemapplication.dto.SuggestionDto;
import cz.upce.votingsystemapplication.dto.SuggestionForMeetingDto;
import cz.upce.votingsystemapplication.model.Suggestion;
import cz.upce.votingsystemapplication.service.SuggestionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class SuggestionRestController {

  private final SuggestionService suggestionService;

  @Autowired
  public SuggestionRestController(SuggestionService suggestionService) {
    this.suggestionService = suggestionService;
  }

  @GetMapping("get-all")
  public List<Suggestion> getAllSuggestions()
  {
    return suggestionService.findAll();
  }

  @GetMapping("get/{id}")
  public SuggestionDto getSuggestion(@PathVariable Long id){
    return suggestionService.findById(id);
  }

  @GetMapping("get/meeting/{id}")
  public List<SuggestionForMeetingDto> getSuggestionsOnMeeting(@PathVariable Long id){
    return suggestionService.findAllSuggestionsOnMeeting(id);
  }

  /*
    Jako parametr se pošle JSON se stejnými atributy, jako má Suggestion. Spring MVC si to už přemapuje sám.
   */
  @PostMapping("add")
  public Suggestion addSuggestion(@RequestBody Suggestion suggestion) {
    Suggestion newSuggestion;
    try {
      newSuggestion = suggestionService.add(suggestion);
    } catch (Exception e) {
      return null;
    }
    return newSuggestion;
  }

  @PostMapping("markAsAccepted/{id}")
  public String markAsAccepted(@PathVariable Long id) {
    try{
      suggestionService.markAsAccepted(id);
    } catch (Exception e) {
      return e.getMessage();
    }
    return "OK";
  }

  @PostMapping("markAsRejected/{id}")
  public String markAsRejected(@PathVariable Long id) {
    try{
      suggestionService.markAsRejected(id);
    } catch (Exception e) {
      return e.getMessage();
    }
    return "OK";
  }

  @DeleteMapping("delete/{id}")
  public String deleteSuggestionById(@PathVariable Long id){
    try {
      suggestionService.deleteById(id);
    } catch (Exception e) {
      return e.getMessage();
    }
    return "OK";
  }

  @DeleteMapping("delete")
  public String deleteSuggestion(@RequestBody Suggestion suggestion){
    try {
      suggestionService.delete(suggestion);
    } catch (Exception e) {
      return e.getMessage();
    }
    return "OK";
  }
}
