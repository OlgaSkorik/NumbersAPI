package com.example.numbersapi.dto;

import com.example.numbersapi.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewUserDTO {
    private String name;
    private String username;
    private String password;

    public User toUser () {
        User user = new User();
        user.setName(name);
        user.setUsername(username);

        return user;
    }
}
