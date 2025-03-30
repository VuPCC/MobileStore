package com.mobilestore.controller;

import com.mobilestore.dto.LoginRequest;
import com.mobilestore.entity.User;
import com.mobilestore.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginRequest request) {
        userService.authenticateUser(request.getUsername(), request.getPassword());
        return ResponseEntity.ok("Login success");
    }
}
