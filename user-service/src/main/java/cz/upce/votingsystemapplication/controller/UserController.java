package cz.upce.votingsystemapplication.controller;

import cz.upce.votingsystemapplication.dto.UserDto;
import cz.upce.votingsystemapplication.dto.UserRegistrationDto;
import cz.upce.votingsystemapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public ModelAndView index() {
        ModelAndView modelView = new ModelAndView("/user-index.html", "", "");
        return modelView;
    }

    @GetMapping("list")
    public ModelAndView list() {

        Iterable<UserDto> users = userService.getAllUsers();

        ModelAndView modelView = new ModelAndView("/user-list.html", "", "");
        modelView.addObject("users", users);
        return modelView;
    }

    @GetMapping("add")
    public ModelAndView addForm() {
        ModelAndView modelView = new ModelAndView("/user-add.html", "userRegistrationDto", new UserRegistrationDto());
        return modelView;
    }

    @PostMapping("add")
    public ModelAndView add(@ModelAttribute UserRegistrationDto user) {
        ModelAndView modelView = null;
        if (!user.getPassword().equals(user.getPasswordCheck())) {
            modelView = new ModelAndView("/user-add.html", "userRegistrationDto", user);
            modelView.addObject("errorMessage", "CHYBA: Zadaná hesla se neshodují!");
        }else if(user.getFirstName().trim().isEmpty() || user.getLastName().trim().isEmpty() || user.getEmail().trim().isEmpty() || user.getPassword().trim().isEmpty() || user.getPasswordCheck().trim().isEmpty()){
            modelView = new ModelAndView("/user-add.html", "userRegistrationDto", user);
            modelView.addObject("errorMessage", "CHYBA: Všechny údaje jsou povinné!");
        }
        else {
            modelView = new ModelAndView("/user-index.html", "", "");
            try {
                userService.registerUser(user);
                modelView.addObject("actionResultMessage", "OK: Uživatel byl zaregistrován.");
            } catch (Exception e) {
                modelView.addObject("actionResultMessage", "CHYBA: Uživatele se nepodařilo zaregistrovat.");

            }
        }
        return modelView;
    }

}
