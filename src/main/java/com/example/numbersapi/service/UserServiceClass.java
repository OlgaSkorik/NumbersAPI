package com.example.numbersapi.service;

import com.example.numbersapi.entity.Role;
import com.example.numbersapi.entity.Status;
import com.example.numbersapi.entity.User;
import com.example.numbersapi.repository.RoleRepository;
import com.example.numbersapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserServiceClass implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceClass(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User registration (User user) {
        Role role = roleRepository.findByTypeOfRole("ROLE_USER");
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        Date now = new Date();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoleList(roleList);
        user.setStatus(Status.ACTIVE);
        user.setCreated(now);
        user.setUpdated(now);
        User newUser = userRepository.save(user);
        log.info("User {} successfully registered!", user.getName());
        return newUser;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = userRepository.findAll();
        log.info("All {} users found!", userList.size());
        return userList;
    }

    @Override
    public User findByUsername(String username) {
        User userFound = userRepository.findByUsername(username);
        log.info("User successfully found by username {}!", username);
        return userFound;
    }

    @Override
    public User findById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            User userFound = userRepository.findById(id).get();
            log.info("User successfully found by id {}!", id);
            return userFound;
        } else {
            log.warn("User is not found by id {}!", id);
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
        log.info("User successfully deleted by id {}!", id);
    }




}
