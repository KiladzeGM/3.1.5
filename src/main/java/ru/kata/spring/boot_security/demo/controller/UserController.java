package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.Service.UserService;
import ru.kata.spring.boot_security.demo.model.User;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String userInformation(Model model, Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        model.addAttribute("userInf", userService.findUserById(user.getId()));
        return "userInf";
    }

}
