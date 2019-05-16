package cz.upce.votingsystemapplication.testsgui.helpers.MeetingStuff;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "meeting-service", fallback = MeetingClientFallback.class)
public interface MeetingClient {

    @RequestMapping(method = RequestMethod.POST, value = "add")
    String add(@RequestBody Meeting meeting);
}