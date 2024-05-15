package com.spring.vidly.dto;

import com.spring.vidly.domain.User;

public record UserDTO(String uuid, String name, String email) {

    public static UserDTO createFromUser(User user){
        return new UserDTO(user.getUuid(), user.getName(), user.getEmail());
    }
}
