package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/user/rest")
public class RestControllerUser {

    private final UserService userServiceImp;

    public RestControllerUser(UserService userServiceImp) {
        this.userServiceImp = userServiceImp;
    }


    @GetMapping()
    public ResponseEntity<User> userInformation(Principal principal) {
        return new ResponseEntity<>((User) userServiceImp.loadUserByUsername(principal.getName()), HttpStatus.OK);
    }

}
