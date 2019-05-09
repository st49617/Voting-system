package cz.upce.votingsystemapplication.controller;

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
    public String login(@RequestBody UserLoginDto user) throws ServletException {

        String jwtToken = "";

//        if (login.getEmail() == null || login.getPasswordHash() == null) {
//            throw new ServletException("Please fill in username and password");
//        }

        String email = user.getEmail();
        String password = user.getPassword();

//
//        User user = userService.(email);
//
//        if (user == null) {
//            throw new ServletException("User email not found.");
//        }
//
//        String pwd = user.getPassword();
//
//        if (!password.equals(pwd)) {
//            throw new ServletException("Invalid login. Please check your name and password.");
//        }

        jwtToken = Jwts.builder().setSubject(email).claim("roles", "user").setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();

        return jwtToken;
    }


}
