package cz.upce.votingsystemapplicationConfigServer.controller;

import cz.upce.votingsystemapplicationConfigServer.dto.UserDto;
import cz.upce.votingsystemapplicationConfigServer.model.User;
import cz.upce.votingsystemapplicationConfigServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("add")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @GetMapping("get/{id}")
    public UserDto getUser(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @GetMapping("get-all")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

}
