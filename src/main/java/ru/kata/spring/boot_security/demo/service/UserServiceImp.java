package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;


    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with this email address was not found");
        }

        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public User findUserById(int id) throws NoSuchElementException {
        User user = null;

        try {
            user = userRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("User with this id was not found");
        }

        return user;
    }

    @Transactional
    @Override
    public boolean userSave(User newUser) {
        userRepository.save(newUser);
        return true;
    }

    @Transactional
    @Override
    public boolean deleteUserById(int id) {
        User user = userRepository.getById(id);

        if (user == null) {
            return false;
        }

        userRepository.delete(user);
        return true;
    }

}
