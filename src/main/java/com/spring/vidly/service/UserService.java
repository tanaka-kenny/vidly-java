package com.spring.vidly.service;

import com.spring.vidly.dto.UserDTO;

public interface UserService {

    UserDTO getUser(String uuid);

    String createUser(UserDTO user);
}
