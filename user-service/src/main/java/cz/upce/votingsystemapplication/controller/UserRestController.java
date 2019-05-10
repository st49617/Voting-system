package cz.upce.votingsystemapplication.controller;

import cz.upce.votingsystemapplication.dto.LoggedInUserDto;
import cz.upce.votingsystemapplication.dto.UserDto;
import cz.upce.votingsystemapplication.dto.UserLoginDto;
import cz.upce.votingsystemapplication.model.User;
import cz.upce.votingsystemapplication.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("add")
    public String addUser(@RequestBody User user) {
        try {
            userService.addUser(user);
        } catch (Exception e) {
            return "Chyba";
        }
        return "OK, uživatel přidán";
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
    public String deleteUser(@PathVariable("id") Long id) {
        try {
            userService.deleteUser(id);
        } catch (Exception e) {
            return "Chyba";
        }
        return "OK, uživatel smazán";
    }

    @PostMapping("/login")
    public LoggedInUserDto login(@RequestBody UserLoginDto user) throws ServletException {

        String jwtToken = "";
        UserDto foundUserDto = null;
        LoggedInUserDto loggedInUserDto = null;
        try {
            foundUserDto = userService.loginUser(user);
            jwtToken = Jwts.builder().setSubject(foundUserDto.getEmail()).claim("roles", "user").setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
            loggedInUserDto = new LoggedInUserDto(foundUserDto, jwtToken);
        } catch (Exception e) {
            System.out.println("CHYBA:" + e.getMessage());
            return new LoggedInUserDto(null, null);
        }
        return loggedInUserDto;
    }


}
