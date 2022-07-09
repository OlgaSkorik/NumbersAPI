package com.example.numbersapi.controller;

import com.example.numbersapi.dto.AdminUserDTO;
import com.example.numbersapi.dto.UserDTO;
import com.example.numbersapi.entity.User;
import com.example.numbersapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/au/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/userById/{id}")
    public ResponseEntity<AdminUserDTO> findUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        AdminUserDTO result = AdminUserDTO.fromUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @GetMapping(value = "usersByUsername/{username}")
//    public ResponseEntity<UserDto> getUserByUsername(@PathVariable(name = "username") String username) {
//        User user = userService.findByUsername(username);
//        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        UserDto result = UserDto.fromUser(user);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
}
