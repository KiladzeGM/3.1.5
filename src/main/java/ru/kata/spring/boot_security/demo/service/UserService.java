package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    @Transactional(readOnly = true)
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findUserById(int id) {
        return userRepository.getById(id);
    }

    @Transactional(readOnly = true)
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public boolean userSave(User newUser) {
        newUser.setRoles(new Role(2, "ROLE_USER"));
        userRepository.save(newUser);
        return true;
    }

    @Transactional
    public boolean userUpdate(User user) {
        userRepository.save(user);
        return true;
    }

    @Transactional
    public boolean deleteUserById(int id) {
        User user = userRepository.getById(id);

        if (user == null) {
            return false;
        }

        userRepository.delete(user);
        return true;
    }

}
