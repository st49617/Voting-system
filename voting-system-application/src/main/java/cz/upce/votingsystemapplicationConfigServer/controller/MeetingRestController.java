package cz.upce.votingsystemapplicationConfigServer.controller;

import cz.upce.votingsystemapplicationConfigServer.dto.MeetingDto;
import cz.upce.votingsystemapplicationConfigServer.model.Meeting;
import cz.upce.votingsystemapplicationConfigServer.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/api/meeting")
public class MeetingRestController {

    private final MeetingService meetingService;

    @Autowired
    public MeetingRestController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @GetMapping("get-all")
    public List<MeetingDto> getAllMeetings() {
        return meetingService.findAll();
    }

    @GetMapping("get/{id}")
    public MeetingDto getMeetingById(@PathVariable Long id) {
        return meetingService.findById(id);
    }

    @PostMapping("add")
    public void addMeeting(@RequestBody Meeting meeting) {
        meetingService.add(meeting);
    }

    @DeleteMapping("delete/{id}")
    public void deleteMeetingById(@PathVariable Long id){
        meetingService.deleteById(id);
    }
}
