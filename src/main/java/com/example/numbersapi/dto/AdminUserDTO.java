package com.example.numbersapi.dto;

import com.example.numbersapi.entity.Status;
import com.example.numbersapi.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AdminUserDTO {
    private Long id;
    private String name;
    private String username;
    private String status;

    public User toUser () {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setUsername(username);
        user.setStatus(Status.valueOf(status));

        return user;
    }

    public static AdminUserDTO fromUser(User user) {
        AdminUserDTO userDTO = new AdminUserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setUsername(user.getUsername());
        userDTO.setStatus(user.getStatus().name());

        return userDTO;
    }
}
