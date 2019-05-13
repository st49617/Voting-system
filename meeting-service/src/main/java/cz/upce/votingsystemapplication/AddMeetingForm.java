package cz.upce.votingsystemapplication;

public class AddMeetingForm {

    private String date;
    private String startTime;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public AddMeetingForm(String date, String startTime) {
        this.date = date;
        this.startTime = startTime;
    }

    public AddMeetingForm() {
    }
}
