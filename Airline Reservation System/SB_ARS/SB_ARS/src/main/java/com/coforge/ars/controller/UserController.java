package com.coforge.ars.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coforge.ars.dto.UserLoginDTO;
import com.coforge.ars.model.User;
import com.coforge.ars.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/ars/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Add User
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {

        User savedUser = userService.addUser(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedUser);
    }

    // Get All Users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {

        return ResponseEntity.ok(userService.getAllUsers());

    }

    // Get User By Id
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(
            @PathVariable Long userId) {

        User user = userService.getUserById(userId);

        return ResponseEntity.ok(user);

    }

    // Update User
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long userId,
            @RequestBody User user) {

        User updatedUser =
                userService.updateUser(userId, user);

        return ResponseEntity.ok(updatedUser);

    }

    // Delete User
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(
            @PathVariable Long userId) {

        userService.deleteUser(userId);

        return ResponseEntity.ok("User Deleted Successfully");

    }

    // Customer Registration
    @PostMapping("/register")
    public ResponseEntity<User> register(
            @RequestBody User user) {

        User savedUser = userService.register(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedUser);

    }

    @PostMapping("/login")
    public ResponseEntity<User> login(
            @RequestBody UserLoginDTO loginDTO) {

        User user = userService.login(
                loginDTO.getEmail(),
                loginDTO.getPassword());

        return ResponseEntity.ok(user);
    }

    // View Wallet Balance
    @GetMapping("/{userId}/wallet")
    public ResponseEntity<Double> getWalletBalance(
            @PathVariable Long userId) {

        Double balance =
                userService.getWalletBalance(userId);

        return ResponseEntity.ok(balance);

    }

    // Add Money to Wallet
    @PutMapping("/{userId}/wallet")
    public ResponseEntity<User> addMoney(
            @PathVariable Long userId,
            @RequestParam Double amount) {

        User user =
                userService.addMoney(userId, amount);

        return ResponseEntity.ok(user);

    }

}