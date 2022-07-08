package com.example.numbersapi.jwt;

import com.example.numbersapi.entity.Role;
import com.example.numbersapi.entity.Status;
import com.example.numbersapi.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GeneratorJWTUser {

    public GeneratorJWTUser() {
    }

    public static JWTUser create(User user) {
        return new JWTUser(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getPassword(),
                user.getStatus().equals(Status.ACTIVE),
                user.getUpdated(),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoleList()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getTypeOfRole())
                ).collect(Collectors.toList());
    }
}
