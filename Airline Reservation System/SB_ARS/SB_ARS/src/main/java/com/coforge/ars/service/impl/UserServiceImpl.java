package com.coforge.ars.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.coforge.ars.exception.UserNotFoundException;
import com.coforge.ars.model.User;
import com.coforge.ars.repo.UserRepo;
import com.coforge.ars.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo repo;
    private Environment environment;

    @Autowired
    public UserServiceImpl(UserRepo repo,
                           Environment environment) {

        this.repo = repo;
        this.environment = environment;
    }

    @Override
    public User addUser(User user) {

        return repo.save(user);

    }

    @Override
    public User updateUser(Long userId,
                           User user) {

        if (!repo.existsById(userId)) {
            throw new UserNotFoundException(
                    environment.getProperty("ars.invalid.user-notfound"));
        }

        user.setUserId(userId);

        return repo.save(user);
    }

    @Override
    public void deleteUser(Long userId) {

        if (!repo.existsById(userId)) {
            throw new UserNotFoundException(
                    environment.getProperty("ars.invalid.user-notfound"));
        }

        repo.deleteById(userId);
    }

    @Override
    public User getUserById(Long userId) {

        if (!repo.existsById(userId)) {
            throw new UserNotFoundException(
                    environment.getProperty("ars.invalid.user-notfound"));
        }

        Optional<User> user = repo.findById(userId);

        return user.get();
    }

    @Override
    public List<User> getAllUsers() {

        return repo.findAll();

    }
    @Override
    public User register(User user) {

        user.setRole("CUSTOMER");

        user.setWalletBalance(100000.0);

        return repo.save(user);
    }

    @Override
    public User login(String email, String password) {

        User user = repo.findByEmail(email);

        if (user == null) {
            throw new UserNotFoundException(
                    environment.getProperty("ars.invalid.user-notfound"));
        }

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException(
                    environment.getProperty("ars.invalid.password"));
        }

        return user;
    }

    @Override
    public Double getWalletBalance(Long userId) {

        if (!repo.existsById(userId)) {
            throw new UserNotFoundException(
                    environment.getProperty("ars.invalid.user-notfound"));
        }

        return repo.findById(userId).get().getWalletBalance();
    }

    @Override
    public User addMoney(Long userId,
                         Double amount) {

        if (!repo.existsById(userId)) {
            throw new UserNotFoundException(
                    environment.getProperty("ars.invalid.user-notfound"));
        }

        User user = repo.findById(userId).get();

        user.setWalletBalance(
                user.getWalletBalance() + amount);

        return repo.save(user);
    }

}