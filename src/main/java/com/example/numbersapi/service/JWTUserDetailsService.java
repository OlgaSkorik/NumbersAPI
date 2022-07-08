package com.example.numbersapi.service;

import com.example.numbersapi.entity.User;
import com.example.numbersapi.jwt.GeneratorJWTUser;
import com.example.numbersapi.jwt.JWTUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JWTUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JWTUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("User with this name not found!");
        }
        JWTUser jwtUser = GeneratorJWTUser.create(user);
        log.info("User with username {} successfully loaded!", username);
        return jwtUser;
    }
}
