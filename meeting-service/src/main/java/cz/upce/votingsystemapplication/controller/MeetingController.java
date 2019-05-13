package cz.upce.votingsystemapplication.controller;

import cz.upce.votingsystemapplication.AddMeetingForm;
import cz.upce.votingsystemapplication.dto.MeetingDto;
import cz.upce.votingsystemapplication.model.Meeting;
import cz.upce.votingsystemapplication.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping("/meeting")
public class MeetingController {

    @Autowired
    MeetingService meetingService;

    @GetMapping("")
    public ModelAndView index() {
        ModelAndView modelView = new ModelAndView("/meeting-index.html", "", "");
        return modelView;
    }

    @GetMapping("list")
    public ModelAndView list() {
        Iterable<MeetingDto> meetings = meetingService.findAll();
        ModelAndView modelView = new ModelAndView("/meeting-list.html", "", "");
        modelView.addObject("meetings", meetings);
        return modelView;
    }

    @GetMapping("add")
    public ModelAndView addForm() {
        AddMeetingForm form = new AddMeetingForm();

        ModelAndView modelView = new ModelAndView("/meeting-add.html", "addMeetingForm", form);
        return modelView;
    }

    @PostMapping("add")
    public ModelAndView add(@ModelAttribute AddMeetingForm form) {
        ModelAndView modelView = null;
        if(form.getDate().trim().isEmpty() || form.getStartTime().trim().isEmpty()){
            modelView = new ModelAndView("/meeting-add.html", "addMeetingForm", form);
            modelView.addObject("errorMessage", "CHYBA: Všechny údaje jsou povinné!");
        }else{
            String startString = form.getDate() + " " + form.getStartTime();
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            Date meetingTime = null;
            try {
                meetingTime = sdf.parse(startString);
            } catch (ParseException e) {
                modelView = new ModelAndView("/meeting-add.html", "addMeetingForm", form);
                modelView.addObject("errorMessage", "CHYBA: Nesprávný formát!");
                return modelView;
            }
            meetingTime = addHoursToJavaUtilDate(meetingTime,2);
            Meeting newMeeting = new Meeting(new Timestamp(meetingTime.getTime()));
            meetingService.add(newMeeting);
            modelView = new ModelAndView("/meeting-index.html", "", "");
            modelView.addObject("actionResultMessage", "OK: Setkání bylo přidáno.");
        }

        return modelView;
    }

    private static Date addHoursToJavaUtilDate(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }



}
