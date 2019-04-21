package cz.upce.votingsystemapplication.service;

import cz.upce.votingsystemapplication.dao.MeetingServiceDAO;
import cz.upce.votingsystemapplication.entity.Suggestion;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class MeetingService {

    private MeetingServiceDAO meetingServiceDAO;
    private List<Suggestion> suggestionList;

    {
        suggestionList = new LinkedList<>();
    }

    public MeetingService(MeetingServiceDAO meetingServiceDAO) {
        this.meetingServiceDAO = meetingServiceDAO;
    }
}
