package cz.upce.votingsystemapplication.client;

import cz.upce.votingsystemapplication.dto.MeetingDto;
import cz.upce.votingsystemapplication.dto.MeetingForSuggestionDto;
import cz.upce.votingsystemapplication.dto.SuggestionForMeetingDto;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class MeetingClientFallback implements MeetingClient {

    @Override
    public MeetingDto get(Long id) {
        return null;
    }

    @Override
    public List<MeetingDto> getAll() {
        return null;
    }

    @Override
    public String add(MeetingDto meetingDto){
        return null;
    }
}
