package cz.upce.votingsystemapplication.dto;

import cz.upce.votingsystemapplication.model.Suggestion.ACCEPTANCE;

public class SuggestionDto extends SuggestionForMeetingDto {

  private MeetingForSuggestionDto meeting;

  public SuggestionDto(){
    super();
  }

  public SuggestionDto(long id, String content, MeetingForSuggestionDto meeting, ACCEPTANCE accepted) {
    super(id, content, accepted);

    this.meeting = meeting;
  }

  public MeetingForSuggestionDto getMeeting() {
    return meeting;
  }

  public void setMeeting(MeetingForSuggestionDto meeting) {
    this.meeting = meeting;
  }
}