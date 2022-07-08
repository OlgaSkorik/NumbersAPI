package com.example.numbersapi.controller;

import com.example.numbersapi.dto.AuthRequestDTO;
import com.example.numbersapi.entity.User;
import com.example.numbersapi.jwt.JWTTokenProvider;
import com.example.numbersapi.service.UserServiceClass;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JWTTokenProvider jwtTokenProvider;
    private final UserServiceClass userService;

//    public AuthenticationController(AuthenticationManager authenticationManager, JWTTokenProvider jwtTokenProvider, UserService userService) {
//        this.authenticationManager = authenticationManager;
//        this.jwtTokenProvider = jwtTokenProvider;
//        this.userService = userService;
//    }

    @PostMapping("/login")
    public ResponseEntity login (@RequestBody AuthRequestDTO authRequestDTO) {
        try {
            String username = authRequestDTO.getUsername();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, authRequestDTO.getPassword()));
            User user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username " + username + " not found!");
            }

            String token = jwtTokenProvider.generateToken(username, user.getRoleList());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException exception) {
            throw new BadCredentialsException("Invalid username and password!");
        }
    }
}
