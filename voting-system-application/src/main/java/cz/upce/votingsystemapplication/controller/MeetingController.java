package cz.upce.votingsystemapplication.controller;

import cz.upce.votingsystemapplication.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MeetingController {

    private final MeetingService meetingService;

    @Autowired
    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @GetMapping("/meetings")
    public String showAllMeetings(Model model) {
        model.addAttribute("meetings", meetingService.findAll());
        return "meeting-list";
    }

    @GetMapping("/meetings/{id}")
    public String getMeetingById(@PathVariable Long id, Model model) {
        model.addAttribute("meeting", meetingService.findById(id));
        return "meeting-detail";
    }
}
