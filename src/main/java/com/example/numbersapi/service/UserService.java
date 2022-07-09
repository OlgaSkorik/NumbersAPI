package com.example.numbersapi.service;

import com.example.numbersapi.entity.User;

import java.util.List;

public interface UserService {
    User registration (User user);
    List<User> findAll();
    User findByUsername (String username);
    User findById (Long id);
    void deleteUserById (Long id);
    void deleteUserByUsername (String username);
}
