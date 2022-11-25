package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> allUsers();

    User findUserById(int id);

    boolean userSave(User newUser);

    boolean deleteUserById(int id);

}
