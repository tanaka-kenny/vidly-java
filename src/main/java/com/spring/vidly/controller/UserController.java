package com.spring.vidly.controller;

import com.spring.vidly.domain.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/{uuid}")
    public User get(@PathVariable String uuid) {
        return new User();
    }

    @PostMapping("/")
    public String createUser(@RequestBody User user) {
        return "ok";
    }
}
