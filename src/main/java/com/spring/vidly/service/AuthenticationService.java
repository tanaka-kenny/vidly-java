package com.spring.vidly.service;

import com.spring.vidly.dto.LoginResponse;
import com.spring.vidly.dto.LoginUserDto;
import com.spring.vidly.dto.RegisterUserDto;
import com.spring.vidly.dto.UserDTO;

public interface AuthenticationService {
    UserDTO signup(RegisterUserDto input);
    LoginResponse authenticate(LoginUserDto input);
}
