package com.coforge.ars.service;

import java.util.List;

import com.coforge.ars.model.User;

public interface UserService {

    User addUser(User user);

    User updateUser(Long userId, User user);

    void deleteUser(Long userId);

    User getUserById(Long userId);

    List<User> getAllUsers();
    
    User register(User user);

    User login(String email, String password);
    
    Double getWalletBalance(Long userId);

    User addMoney(Long userId, Double amount);
}