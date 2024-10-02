package com.spring.vidly.controller;

import com.spring.vidly.dto.LoginResponse;
import com.spring.vidly.dto.LoginUserDto;
import com.spring.vidly.dto.RegisterUserDto;
import com.spring.vidly.dto.UserDTO;
import com.spring.vidly.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public UserDTO signup(@RequestBody RegisterUserDto registerUserDto) {
        return authenticationService.signup(registerUserDto);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginUserDto loginUserDto) {
        return authenticationService.authenticate(loginUserDto);
    }
}
