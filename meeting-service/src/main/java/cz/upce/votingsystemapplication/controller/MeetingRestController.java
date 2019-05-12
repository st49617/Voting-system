package cz.upce.votingsystemapplication.controller;

import cz.upce.votingsystemapplication.dto.MeetingDto;
import cz.upce.votingsystemapplication.model.Meeting;
import cz.upce.votingsystemapplication.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
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
    public String addMeeting(@RequestBody Meeting meeting) {
        try {
            meetingService.add(meeting);
        } catch (Exception ex) {
            return "ERROR:\n" + ex.getMessage();
        }

        return "OK";
    }

    @DeleteMapping("delete/{id}")
    public MeetingDto deleteMeetingById(@PathVariable Long id){
        MeetingDto removed = meetingService.findById(id);

        meetingService.deleteById(id);

        return removed;
    }
}
