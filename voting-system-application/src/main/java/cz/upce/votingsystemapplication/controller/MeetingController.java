package cz.upce.votingsystemapplication.controller;

import cz.upce.votingsystemapplication.model.Meeting;
import cz.upce.votingsystemapplication.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.CannotProceedException;
import java.util.List;

@RestController
@RequestMapping(value = "/meeting")
public class MeetingController {

    private final MeetingService meetingService;

    @Autowired
    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @GetMapping
    public List<Meeting> getAllMeetings() {
        return meetingService.findAll();
    }

    @GetMapping("get/{id}")
    public Meeting getMeetingById(@PathVariable Long id) {
        return meetingService.findById(id);
    }

    @GetMapping("add/{date}")
    public void addMeeting(@PathVariable("date") String date) throws CannotProceedException {
        meetingService.add(date);
    }
}
