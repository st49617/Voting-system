package cz.upce.votingsystemapplication.controller;

import cz.upce.votingsystemapplication.dto.SuggestionDto;
import cz.upce.votingsystemapplication.dto.SuggestionForMeetingDto;
import cz.upce.votingsystemapplication.model.Suggestion;
import cz.upce.votingsystemapplication.service.SuggestionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/suggestion")
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
  public void addSuggestion(@RequestBody Suggestion suggestion){
    suggestionService.add(suggestion);
  }

  @PostMapping("markAsAccepted/{id}")
  public void markAsAccepted(@PathVariable Long id) {
    suggestionService.markAsAccepted(id);
  }

  @PostMapping("markAsRejected/{id}")
  public void markAsRejected(@PathVariable Long id) {
    suggestionService.markAsRejected(id);
  }

  @DeleteMapping("delete/{id}")
  public void deleteSuggestionById(@PathVariable Long id){
    suggestionService.deleteById(id);
  }

  @DeleteMapping("delete")
  public void deleteSuggestion(@RequestBody Suggestion suggestion){
    suggestionService.delete(suggestion);
  }
}
