package cz.upce.votingsystemapplication.dto;

public class SuggestionDto extends SuggestionForMeetingDto {

  private MeetingForSuggestionDto meeting;

  public SuggestionDto(){
    super();
  }

  public SuggestionDto(long id, String content, MeetingForSuggestionDto meeting, Boolean accepted) {
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