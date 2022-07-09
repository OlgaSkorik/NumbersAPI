package com.example.numbersapi.mapper;

import com.example.numbersapi.dto.NewUserDTO;
import com.example.numbersapi.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper{

    public NewUserDTO toUserDTO(User user) {
        return null;
    }

    public User toUser(NewUserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());

        return user;
    }

    public List<NewUserDTO> toUserDTOList(List<User> users) {
        return null;
    }
}
