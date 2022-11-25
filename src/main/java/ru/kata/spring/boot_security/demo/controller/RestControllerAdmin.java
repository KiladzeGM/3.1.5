package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/admin/rest")
public class RestControllerAdmin {

    private final UserService userServiceImp;

    public RestControllerAdmin(UserService userServiceImp) {
        this.userServiceImp = userServiceImp;
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userServiceImp.allUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable int id) {
        return new ResponseEntity<>(userServiceImp.findUserById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody User user) {
        userServiceImp.userSave(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable int id) {
        userServiceImp.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
