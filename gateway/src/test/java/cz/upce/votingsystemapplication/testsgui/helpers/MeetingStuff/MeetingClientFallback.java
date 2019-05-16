package cz.upce.votingsystemapplication.testsgui.helpers.MeetingStuff;

import org.springframework.stereotype.Component;

@Component
public class MeetingClientFallback implements MeetingClient {
    @Override
    public String add(Meeting meeting){
        return null;
    }
}
