package cz.upce.votingsystemapplication.controller;

import cz.upce.votingsystemapplication.entity.Meeting;
import cz.upce.votingsystemapplication.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;

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

    @GetMapping("/meetings/show/{id}")
    public String getMeetingById(@PathVariable Long id, Model model) {
        model.addAttribute("meeting", meetingService.findById(id));
        return "meeting-detail";
    }

    @GetMapping("/meetings/add/{date}")
    public void addMeeting(@PathVariable("date") String date, ModelMap model) {
        meetingService.add(Date.valueOf(date));
    }
}
