package cz.upce.votingsystemapplication.dto;

import cz.upce.votingsystemapplication.model.Meeting;

public class SuggestionDto {

  private long id;
  private String content;
  private Meeting meeting;
  private Boolean accepted;

  public SuggestionDto(){
  }

  public SuggestionDto(long id, String content, Meeting meeting, Boolean accepted) {
    this.id = id;
    this.content = content;
    this.meeting = meeting;
    this.accepted = accepted;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Meeting getMeeting() {
    return meeting;
  }

  public void setMeeting(Meeting meeting) {
    this.meeting = meeting;
  }

  public Boolean getAccepted() {
    return accepted;
  }

  public void setAccepted(Boolean accepted) {
    this.accepted = accepted;
  }
}
