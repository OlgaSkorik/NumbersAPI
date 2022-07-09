package com.example.numbersapi.dto;

import com.example.numbersapi.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class UserDTO {
    private Long id;
    private String name;
    private String username;

    public User toUser () {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setUsername(username);

        return user;
    }

    public static UserDTO fromUser(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setUsername(user.getUsername());

        return userDTO;
    }
}
