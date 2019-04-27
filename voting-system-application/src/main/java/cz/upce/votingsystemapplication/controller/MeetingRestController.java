package cz.upce.votingsystemapplication.controller;

import cz.upce.votingsystemapplication.model.Meeting;
import cz.upce.votingsystemapplication.service.MeetingService;
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
    public List<Meeting> getAllMeetings() {
        return meetingService.findAll();
    }

    @GetMapping("get/{id}")
    public Meeting getMeetingById(@PathVariable Long id) {
        return meetingService.findById(id);
    }

    @PostMapping("add")
    public void addMeeting(@RequestBody Meeting meeting) {
        meetingService.add(meeting);
    }
}
