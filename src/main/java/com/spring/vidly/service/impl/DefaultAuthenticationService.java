package com.spring.vidly.service.impl;

import com.spring.vidly.domain.User;
import com.spring.vidly.dto.LoginResponse;
import com.spring.vidly.dto.LoginUserDto;
import com.spring.vidly.dto.RegisterUserDto;
import com.spring.vidly.dto.UserDTO;
import com.spring.vidly.reposity.UserRepository;
import com.spring.vidly.service.AuthenticationService;
import com.spring.vidly.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultAuthenticationService implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Value("${security.jwt.expiration-time}")
    private long expirationTime;

    @Override
    public UserDTO signup(RegisterUserDto input) {
        User user = new User();
        user.setName(input.name());
        user.setPassword(passwordEncoder.encode(input.password()));
        user.setEmail(input.email());
        user.setUuid(UUID.randomUUID().toString());


        user = userRepository.save(user);
        return UserDTO.createFromUser(user);
    }

    @Override
    public LoginResponse authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.email(),
                        input.password()
                )
        );

        Optional<User> byEmail = userRepository.findByEmail(input.email());
        if (byEmail.isPresent()) {
            User user = byEmail.get();
            String token = jwtService.generateToken(user);
            return new LoginResponse(token, expirationTime);
        }
        return null;
    }
}
