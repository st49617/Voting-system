package cz.upce.votingsystemapplication.client;

import cz.upce.votingsystemapplication.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallback implements UserClient {

    @Override
    public UserDto getUser(Long userId) {
        return null;
    }
}
