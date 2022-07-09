package com.example.numbersapi.controller;

import com.example.numbersapi.dto.UserDTO;
import com.example.numbersapi.entity.User;
import com.example.numbersapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/au/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        UserDTO result = UserDTO.fromUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<User> save (@RequestBody User user) {
        return new ResponseEntity<>(userService.registration(user), HttpStatus.CREATED);
    }

    @PutMapping("/update/{username}")
    public ResponseEntity<User> updateUser (@PathVariable String username, @RequestBody User myNewUser) {
        User myUser = userService.findByUsername(username);
        if (myUser != null) {
            myNewUser.setId(myUser.getId());
            User updateUser = userService.registration(myNewUser);
            return ResponseEntity.ok(updateUser);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/deleteByUsername/{username}")
    public ResponseEntity<User> deleteUserByUsername(@PathVariable String username) {
        userService.deleteUserByUsername(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
