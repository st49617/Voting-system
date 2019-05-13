package cz.upce.votingsystemapplication.client;

import cz.upce.votingsystemapplication.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "user-service", fallback = UserClientFallback.class)
public interface UserClient {

    @RequestMapping(method = RequestMethod.GET, value = "get/{id}")
    UserDto getUser(@PathVariable("id") Long userId);
}